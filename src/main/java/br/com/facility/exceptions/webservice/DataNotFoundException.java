package br.com.facility.exceptions.webservice;

import org.springframework.http.HttpStatus;

public class DataNotFoundException extends FacilityWSBaseException {

	public DataNotFoundException() {
		super(HttpStatus.NOT_FOUND, "message", "cause");
	}
}
