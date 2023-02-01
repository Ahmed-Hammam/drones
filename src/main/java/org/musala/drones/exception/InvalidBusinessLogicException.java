package org.musala.drones.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

public class InvalidBusinessLogicException extends BusinessException{

	private static final long serialVersionUID = 1L;

	@Getter
	private HttpStatus status;
	
	public InvalidBusinessLogicException(String msg, HttpStatus status) {
		super(msg, status);
		this.status = status;
	}
}
