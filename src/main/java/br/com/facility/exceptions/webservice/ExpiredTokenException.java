package br.com.facility.exceptions.webservice;

import org.springframework.http.HttpStatus;

public class ExpiredTokenException extends FacilityWSBaseException {

	public ExpiredTokenException() {
		super(HttpStatus.BAD_REQUEST, "Sessão expirada, Fazer login novamente", "Sessão expirada");
	}
}
