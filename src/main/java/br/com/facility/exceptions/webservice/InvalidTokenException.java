package br.com.facility.exceptions.webservice;

import org.springframework.http.HttpStatus;

public class InvalidTokenException extends FacilityWSBaseException {

	public InvalidTokenException() {
		super(HttpStatus.UNAUTHORIZED, "Token Inválido", "Token vazio, para fazer requisições é necessário possuir o token de acesso");
	}
}
