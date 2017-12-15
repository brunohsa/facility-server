package br.com.facility.exceptions.webservice;

import org.springframework.http.HttpStatus;

public class InvalidDateFormatException extends FacilityWSBaseException {

	public InvalidDateFormatException() {
		super(HttpStatus.BAD_REQUEST, "Formato da data invalido", "Formato Invalido");
	}
}
