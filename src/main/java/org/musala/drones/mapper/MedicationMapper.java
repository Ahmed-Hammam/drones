package org.musala.drones.mapper;

import java.util.Objects;

import org.musala.drones.dto.MedicationDto;
import org.musala.drones.model.MedicationModel;
import org.springframework.stereotype.Component;

@Component
public class MedicationMapper implements EntityDtoMapper<MedicationDto, MedicationModel>{

	@Override
	public MedicationDto mapToDto(MedicationModel obj) {
		return MedicationDto.builder()
				.code(Objects.nonNull(obj.getCode()) == true ?obj.getCode():null)
				.name(Objects.nonNull(obj.getName()) == true ?obj.getName():null)
				.image(Objects.nonNull(obj.getImage()) == true ?obj.getImage():null)
				.weight(Objects.nonNull(obj.getWeight()) == true ?obj.getWeight():0)
				.build();
	}

	@Override
	public MedicationModel mapToEntity(MedicationDto obj) {
		MedicationModel medicationModel = new MedicationModel();
		medicationModel.setCode(Objects.nonNull(obj.getCode()) == true ?obj.getCode():null);
		medicationModel.setName(Objects.nonNull(obj.getName()) == true ?obj.getName():null);
		medicationModel.setImage(Objects.nonNull(obj.getImage()) == true ?obj.getImage():null);
		medicationModel.setWeight(Objects.nonNull(obj.getWeight()) == true ?obj.getWeight():0);
		return medicationModel;
	}

}
