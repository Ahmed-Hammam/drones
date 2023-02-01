package org.musala.drones.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "medication")
@NoArgsConstructor
@Data
public class MedicationModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "medication_id")
	private Long id;
	
	@Setter
	@Getter
	@Column(nullable = false)
	private String name;
	
	@Setter
	@Getter
	@Column(nullable = false)
	private String code;
	
	@Setter
	@Getter
	@Column(nullable = false)
	private Integer weight;
	
	@Setter
	@Getter
	@Column
	private String image;
	
	@Setter
	@ManyToOne(fetch = FetchType.LAZY)
	private DroneModel drone;
	
}
