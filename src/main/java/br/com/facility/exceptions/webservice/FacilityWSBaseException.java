package br.com.facility.exceptions.webservice;

import org.springframework.http.HttpStatus;

public class FacilityWSBaseException extends RuntimeException {

	private HttpStatus httpStatus;

	public FacilityWSBaseException(HttpStatus httpStatus, String message) {
		super(message);
		this.httpStatus = httpStatus;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}
