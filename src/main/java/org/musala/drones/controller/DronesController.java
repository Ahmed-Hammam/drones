package org.musala.drones.controller;

import java.util.List;

import javax.validation.Valid;

import org.musala.drones.dto.DroneDto;
import org.musala.drones.dto.MedicationDto;
import org.musala.drones.service.DroneService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/v1/drones")
public class DronesController {

	private final DroneService droneService;
	
	// registering a drone;
	@PostMapping
	ResponseEntity<DroneDto> registerDrone(@Valid @RequestBody DroneDto droneDto){
		return ResponseEntity.ok(droneService.registerDrone(droneDto));
	}

	// loading a drone with medication items;
	@PutMapping(value = "/{serial}")
	ResponseEntity<DroneDto> loadDroneWithMedicationItems(@PathVariable String serial, @Valid @RequestBody List<MedicationDto> medicationDto){
		return ResponseEntity.ok(droneService.loadDroneWithMedicationItems(serial, medicationDto));
	}
	
	//checking loaded medication items for a given drone; 
	@GetMapping(value = "/{serial}")
	ResponseEntity<DroneDto> checkingLoadedDroneMedicationItems(@PathVariable String serial){
		return ResponseEntity.ok(droneService.checkDroneMedicationItems(serial));
	}
	
	//checking available drones for loading;
	@GetMapping(value = "/available")
	ResponseEntity<List<DroneDto>> checkAvailableDrones(){
		return ResponseEntity.ok(droneService.getAvailableDrones());
	}

	//check drone battery level for a given drone;
	@GetMapping(value = "/battery/{serial}")
	ResponseEntity<String> checkDroneBattery(@PathVariable String serial){
		return ResponseEntity.ok(droneService.checkDroneBatteryLevel(serial));
	}
}
