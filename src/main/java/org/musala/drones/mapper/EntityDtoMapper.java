package org.musala.drones.mapper;

public interface EntityDtoMapper<T1, T2> {

	T1 mapToDto(T2 obj);
	T2 mapToEntity(T1 obj);
}
