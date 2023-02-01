package org.musala.drones.repository;

import org.musala.drones.model.DroneAuditModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneAuditRepository extends JpaRepository<DroneAuditModel, Long>{

}
