package com.exception;

import org.springframework.http.HttpStatus;

public class HandlerException extends RuntimeException {
	
	private static final long serialVersionUID = -8521396473569596675L;
	
	private final String message;
	private final HttpStatus httpStatus;

	public HandlerException(String message, HttpStatus httpStatus) {
		this.message = message;
		this.httpStatus = httpStatus;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

}
