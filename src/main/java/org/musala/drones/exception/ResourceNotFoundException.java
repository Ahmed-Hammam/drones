package org.musala.drones.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

public class ResourceNotFoundException extends BusinessException {

	private static final long serialVersionUID = 1L;

	@Getter
	private HttpStatus status;
	
	public ResourceNotFoundException(String msg, HttpStatus status) {
		super(msg,status);
		this.status = status;
	}
}
