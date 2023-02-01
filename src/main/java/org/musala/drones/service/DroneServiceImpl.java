package org.musala.drones.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.musala.drones.constants.DroneStateEnum;
import org.musala.drones.dto.DroneDto;
import org.musala.drones.dto.MedicationDto;
import org.musala.drones.exception.InvalidBusinessLogicException;
import org.musala.drones.exception.ResourceDuplicationViolationException;
import org.musala.drones.exception.ResourceNotFoundException;
import org.musala.drones.mapper.DroneMapper;
import org.musala.drones.mapper.MedicationMapper;
import org.musala.drones.model.DroneModel;
import org.musala.drones.model.MedicationModel;
import org.musala.drones.repository.DroneRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DroneServiceImpl implements DroneService{

	private DroneRepository droneRepository;
	private DroneMapper droneMapper;
	private MedicationMapper medicationMapper;
	
	@Override
	public DroneDto registerDrone(DroneDto drone) {
		if(droneRepository.findBySerial(drone.getSerial()).isPresent())
			throw new ResourceDuplicationViolationException("Drone with the same serial already exist", HttpStatus.INTERNAL_SERVER_ERROR);
		checkDroneWeight(drone.getMedications(),drone.getWeight());
		DroneModel savedDrone = droneMapper.mapToEntity(drone);
		savedDrone.setState(DroneStateEnum.IDLE);
		if(Objects.nonNull(savedDrone.getMedications()) && !savedDrone.getMedications().isEmpty())
			savedDrone.getMedications().forEach(m -> m.setDrone(savedDrone));
		 droneRepository.save(savedDrone);
		return droneMapper.mapToDto(savedDrone);
	}

	@Override
	public DroneDto loadDroneWithMedicationItems(String droneSerial, List<MedicationDto> medicationDtos) {
		 Optional<DroneModel> droneOptional = droneRepository.getDronesWithMedicationList(droneSerial);
		 final DroneModel droneModel = droneOptional.orElseThrow(() -> new ResourceNotFoundException("No Drone found with the following serial",HttpStatus.NOT_FOUND));
		 if(droneModel.getBattery() < 25)
			 throw new InvalidBusinessLogicException("Cannot load drone with medications as battery is less than 25 %", HttpStatus.INTERNAL_SERVER_ERROR);
		 
		 if(Objects.isNull(medicationDtos) || medicationDtos.isEmpty())
			 return droneMapper.mapToDto(droneModel);
		 
		 if(droneModel.getMedications().isEmpty() || Objects.isNull(droneModel.getMedications()))
			 checkDroneWeight(medicationDtos,droneModel.getWeight());
		 else
			 checkDroneWeight(medicationDtos, droneModel.getMedications(), droneModel.getWeight());
		 
		 List<MedicationModel> medications = medicationDtos.stream().map(medicationMapper::mapToEntity)
				 .collect(Collectors.toList());
		 
		 medications.forEach(m -> m.setDrone(droneModel));
		 List<MedicationModel> newMedicationModels = new ArrayList<>();
		 newMedicationModels.addAll(medications);
		 newMedicationModels.addAll(droneModel.getMedications());
		 droneModel.setMedications(newMedicationModels);
		 if(droneModel.getWeight() < 500)
			 droneModel.setState(DroneStateEnum.LOADING);
		 else
			 droneModel.setState(DroneStateEnum.LOADED);
		 
		return droneMapper.mapToDto(droneRepository.save(droneModel));
	}

	@Override
	public DroneDto checkDroneMedicationItems(String droneSerial) {
		 Optional<DroneModel> droneOptional = droneRepository.getDronesWithMedicationList(droneSerial);
		 DroneModel droneModel = droneOptional.orElseThrow(() -> new ResourceNotFoundException("No Drone found with the following serial",HttpStatus.NOT_FOUND));
		return droneMapper.mapToDto(droneModel);
	}

	@Override
	public List<DroneDto> getAvailableDrones() {
		 List<DroneModel> drones = droneRepository.findByState(DroneStateEnum.IDLE);
		if(Objects.nonNull(drones) && !drones.isEmpty())
			return drones.stream().map(droneMapper::mapToAbstractDto).collect(Collectors.toList());
		else return new ArrayList<>(1);
			
	}

	@Override
	public String checkDroneBatteryLevel(String droneSerial) {
		 Optional<DroneModel> droneOptional = droneRepository.findBySerial(droneSerial);
		 DroneModel droneModel = droneOptional.orElseThrow(() -> new ResourceNotFoundException("No Drone found with the following serial",HttpStatus.NOT_FOUND));
		return droneModel.getBattery()+" %";
	}

	private void checkDroneWeight( List<MedicationDto> medicationDtos, int weight) {
		if(weight > 500)
			throw new InvalidBusinessLogicException("Invalid weight ! drone weight must be less than 500",HttpStatus.INTERNAL_SERVER_ERROR);
		if(Objects.nonNull(medicationDtos) && !medicationDtos.isEmpty()) {
			int medicationsWeight = medicationDtos.stream().mapToInt(m -> m.getWeight()).sum();
			if(medicationsWeight > 500)
				throw new InvalidBusinessLogicException("Invalid weight ! drone weight must be less than 500",HttpStatus.INTERNAL_SERVER_ERROR);
			if(medicationsWeight > weight)
				throw new InvalidBusinessLogicException("Invalid weight ! medications total weights must be less than drone weight",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	private void checkDroneWeight(List<MedicationDto> medicationDtos, List<MedicationModel> medications,
			Integer weight) {
		int newMedicationsWeight = 0, oldMedicationsWeight = 0;
		if(Objects.nonNull(medicationDtos) && !medicationDtos.isEmpty()) {
			 newMedicationsWeight = medicationDtos.stream().mapToInt(m -> m.getWeight()).sum();
		}
		if(Objects.nonNull(medications) && !medications.isEmpty()) {
			 oldMedicationsWeight = medications.stream().mapToInt(m -> m.getWeight()).sum();
		}
		if((oldMedicationsWeight + newMedicationsWeight) > weight)
			throw new InvalidBusinessLogicException("Invalid weight ! medications total weights must be less than drone weight",HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
}
