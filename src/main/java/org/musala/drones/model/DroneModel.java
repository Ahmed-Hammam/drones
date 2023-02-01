package org.musala.drones.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.musala.drones.constants.DroneModelEnum;
import org.musala.drones.constants.DroneStateEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "drones", indexes = @Index(name = "drone_ser_idx", columnList = "serial"))
@NoArgsConstructor
public class DroneModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "drone_id")
	private Long id;
	
	@Setter
	@Getter
	@Column(length = 100, unique = true)
	private String serial;
	
	@Setter
	@Getter
	@Column(nullable = false, name = "model")
	@Enumerated(EnumType.ORDINAL)
	private DroneModelEnum model;
	
	@Setter
	@Getter
	@Column(nullable = false)
	private Integer weight;
	
	@Setter
	@Getter
	@Column(nullable = false)
	private Integer battery;

	@Setter
	@Getter
	@Column(nullable = false, name = "state")
	@Enumerated(EnumType.ORDINAL)
	private DroneStateEnum state;
	
	@Setter
	@Getter
	@OneToMany(mappedBy = "drone", cascade = {CascadeType.PERSIST,CascadeType.MERGE}, fetch = FetchType.LAZY)
	private List<MedicationModel> medications;
	
}
