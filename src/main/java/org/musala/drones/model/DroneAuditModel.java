package org.musala.drones.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.musala.drones.constants.DroneBatteryStateEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "drone_audit")
@NoArgsConstructor
public class DroneAuditModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Setter
	@Getter
	@Column(length = 100, nullable = false, name = "drone_serial")
	private String serial;
	
	@Setter
	@Getter
	@Column(nullable = false, name = "drone_battery")
	private Integer battery;
	
	@Setter
	@Getter
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, name = "battery_state")
	private DroneBatteryStateEnum batteryStateEnum;
	
	@Setter
	@Getter
	@Column(name = "created_on", nullable = false)
	private LocalDateTime createdAt = LocalDateTime.now();
	
	@Setter
	@Getter
	@Column(name = "updated_on", nullable = false)
	private LocalDateTime updatedAt;
	
}
