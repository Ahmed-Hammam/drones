package org.musala.drones.repository;

import java.util.List;
import java.util.Optional;

import org.musala.drones.constants.DroneStateEnum;
import org.musala.drones.model.DroneModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DroneRepository extends JpaRepository<DroneModel, Long>{

	@Query("select d from DroneModel d LEFT JOIN FETCH d.medications where d.serial = ?1")
	Optional<DroneModel> getDronesWithMedicationList(String serial);
	
	Optional<DroneModel> findBySerial(String serial);
	
	List<DroneModel> findByState(DroneStateEnum model);
}
