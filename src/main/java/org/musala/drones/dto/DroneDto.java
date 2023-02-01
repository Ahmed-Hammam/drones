package org.musala.drones.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.musala.drones.constants.DroneModelEnum;
import org.musala.drones.constants.DroneStateEnum;
import org.musala.drones.util.EnumValidator;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@JsonInclude(value = Include.NON_NULL)
public class DroneDto implements Serializable{

	private static final long serialVersionUID = 1L;

	@Setter
	@NotBlank(message = "Invalid drone serial")
	@JsonProperty
	private String serial;
	
	@NotBlank(message = "Invalid drone model")
	@JsonProperty
	@EnumValidator(enumClass = DroneModelEnum.class)
	private String model;
	
	@Setter
	@JsonProperty
	@Max(value = 500, message = "Drone weight must not exceed 500 gr")
	@Min(value = 1, message = "Drone weight must not be less than 10 gr")
	private int weight;
	
	@Setter
	@Min(value = 10, message = "Drone battery must not be less than 10 %")
	@Max(value = 100, message = "Drone weight must not exceed 500 gr")
	@JsonProperty
	private int battery;
	
	@NotBlank(message = "Invalid drone state")
	@JsonProperty
	@EnumValidator(enumClass = DroneStateEnum.class)
	private String state;
	
	@Setter
	@JsonProperty
	@Valid
	private List<MedicationDto> medications;
	
	
	
}
