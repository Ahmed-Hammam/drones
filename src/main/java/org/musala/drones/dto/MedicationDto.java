package org.musala.drones.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
@JsonInclude(value = Include.NON_NULL)
public class MedicationDto {

	@JsonProperty
	@Pattern(regexp = "[A-Za-z0-9\\-\\_]+",message = "Invalid medication name, allowed only letters, numbers, ‘-‘, ‘_’")
	@NotBlank(message = "Invalid medication name")
	private String name;
	
	@JsonProperty
	@Pattern(regexp = "[A-Z0-9\\-\\_]+", message = "Invalid code, allowed only upper case letters, underscore and numbers")
	@NotBlank(message = "Invalid medication code")
	private String code;
	
	@JsonProperty
	@Max(value = 500, message = "Invalid weight. drone max weight is 500 gr")
	private int weight;
	
	@JsonProperty
	private String image;
	
}
