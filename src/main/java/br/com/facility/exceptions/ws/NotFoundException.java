package br.com.facility.exceptions.ws;

import org.springframework.http.HttpStatus;

public class NotFoundException extends RuntimeException {

	private HttpStatus httpStatus;

	public NotFoundException(HttpStatus httpStatus, String message) {
		super(message);
		this.httpStatus = httpStatus;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}
