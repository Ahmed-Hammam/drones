package org.musala.drones.service;

import java.util.List;

import org.musala.drones.dto.DroneDto;
import org.musala.drones.dto.MedicationDto;

public interface DroneService {

	DroneDto registerDrone(DroneDto drone);
	DroneDto loadDroneWithMedicationItems(String droneSerial, List<MedicationDto> medications);
	DroneDto checkDroneMedicationItems(String droneSerial);
	List<DroneDto> getAvailableDrones();// TODO pagination
	String checkDroneBatteryLevel(String droneSerial);
	/*
	 * - registering a drone;
- loading a drone with medication items;
- checking loaded medication items for a given drone; 
- checking available drones for loading;
- check drone battery level for a given drone;
	 */
}
