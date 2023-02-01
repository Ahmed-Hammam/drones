package org.musala.drones.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.musala.drones.constants.DroneBatteryStateEnum;
import org.musala.drones.mapper.BasicMapper;
import org.musala.drones.model.DroneAuditModel;
import org.musala.drones.model.DroneModel;
import org.musala.drones.repository.DroneAuditRepository;
import org.musala.drones.repository.DroneRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@AllArgsConstructor
@Slf4j
public class DroneBatteyAuditServiceImpl implements DroneBatteyAuditService, BasicMapper<DroneAuditModel, DroneModel>{

	private final DroneRepository droneRepository;
	private final DroneAuditRepository droneAuditRepository;
	
	@Override
	@Scheduled(cron = "${app.scheduling.rate}")
	public void logBatteryLevel() {
		log.info("Auditing drone battery level ..");
		List<DroneModel> drones = droneRepository.findAll();
		if(Objects.nonNull(drones) && !drones.isEmpty()) {
			List<DroneAuditModel> audits = drones.stream().map(this::map).collect(Collectors.toList());
			droneAuditRepository.saveAll(audits);
			log.info("Auditing completed successfully ..");			
		}
	}

	@Override
	public DroneAuditModel map(DroneModel obj) {
		DroneAuditModel droneAudit = new DroneAuditModel();
		droneAudit.setBattery(obj.getBattery());
		droneAudit.setSerial(obj.getSerial());
		if(obj.getBattery() > 25 && obj.getBattery() < 75)
			droneAudit.setBatteryStateEnum(DroneBatteryStateEnum.NORMAL);
		if(obj.getBattery() < 25)
			droneAudit.setBatteryStateEnum(DroneBatteryStateEnum.LOW);
		else
			droneAudit.setBatteryStateEnum(DroneBatteryStateEnum.HIGH);
		droneAudit.setUpdatedAt(LocalDateTime.now());
		return droneAudit;
	}

	
}
