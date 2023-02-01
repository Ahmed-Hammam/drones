package org.musala.drones.mapper;

import java.util.Objects;
import java.util.stream.Collectors;

import org.musala.drones.constants.DroneModelEnum;
import org.musala.drones.constants.DroneStateEnum;
import org.musala.drones.dto.DroneDto;
import org.musala.drones.dto.MedicationDto;
import org.musala.drones.model.DroneModel;
import org.musala.drones.model.MedicationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DroneMapper implements EntityDtoMapper<DroneDto, DroneModel>{

	@Autowired
	private EntityDtoMapper<MedicationDto, MedicationModel> medicationMapper;
	
	@Override
	public DroneDto mapToDto(DroneModel obj) {
		return DroneDto.builder()
		.serial(Objects.nonNull(obj.getSerial()) == true ?obj.getSerial():null)
		.battery(Objects.nonNull(obj.getBattery()) == true ?obj.getBattery():null)
		.model(Objects.nonNull(obj.getModel()) == true ?obj.getModel().name():null)
		.state(Objects.nonNull(obj.getState()) == true ?obj.getState().name():null)
		.weight(Objects.nonNull(obj.getWeight()) == true ?obj.getWeight():null)
		.medications(Objects.nonNull(obj.getMedications()) == true ?obj.getMedications().stream().map(medicationMapper::mapToDto).collect(Collectors.toList()):null)
		.build();
	}

	public DroneDto mapToAbstractDto(DroneModel obj) {
		return DroneDto.builder()
		.serial(Objects.nonNull(obj.getSerial()) == true ?obj.getSerial():null)
		.battery(Objects.nonNull(obj.getBattery()) == true ?obj.getBattery():null)
		.model(Objects.nonNull(obj.getModel()) == true ?obj.getModel().name():null)
		.state(Objects.nonNull(obj.getState()) == true ?obj.getState().name():null)
		.weight(Objects.nonNull(obj.getWeight()) == true ?obj.getWeight():null)
		.build();
	}
	
	@Override
	public DroneModel mapToEntity(DroneDto obj) {
		DroneModel droneModel = new DroneModel();
		droneModel.setSerial(Objects.nonNull(obj.getSerial()) == true ?obj.getSerial():null);
		droneModel.setBattery(Objects.nonNull(obj.getBattery()) == true ?obj.getBattery():null);
		droneModel.setModel(Objects.nonNull(obj.getModel()) == true ?DroneModelEnum.valueOf(obj.getModel()):null);
		droneModel.setState(Objects.nonNull(obj.getState()) == true ?DroneStateEnum.valueOf(obj.getState()):null);
		droneModel.setWeight(Objects.nonNull(obj.getWeight()) == true ?obj.getWeight():null);
		droneModel.setMedications(Objects.nonNull(obj.getMedications()) == true ?obj.getMedications().stream().map(medicationMapper::mapToEntity).collect(Collectors.toList()):null);
		return droneModel;
	}

	
}
