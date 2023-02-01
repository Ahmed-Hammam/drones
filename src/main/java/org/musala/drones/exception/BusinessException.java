package org.musala.drones.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

public class BusinessException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	@Getter
	private HttpStatus status;
	
	public BusinessException(String msg, HttpStatus status) {
		super(msg);
		this.status = status;
	}
}
