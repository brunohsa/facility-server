package br.com.facility.exceptions;

public class InvalidTokenException extends FacilityBaseException {

	public InvalidTokenException() {
		super("Token Inválido", "Token vazio, para fazer requisições é necessário possuir o token de acesso");
	}
}
