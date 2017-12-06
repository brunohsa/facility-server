package br.com.facility.exceptions;

public class InvalidDateFormatException extends FacilityBaseException {

	public InvalidDateFormatException() {
		super("Formato da data invalido", "Formato Invalido");
	}
}
