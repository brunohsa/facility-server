package br.com.facility.exceptions;

public class ExpiredTokenException extends FacilityBaseException {

	public ExpiredTokenException() {
		super("Sessão expirada, Fazer login novamente", "Sessão expirada");
	}
}
