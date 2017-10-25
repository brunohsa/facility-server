package br.com.facility.exceptions;

public class InvalidTokenException extends FacilityBaseException {

	public InvalidTokenException(String message, String cause) {
		super(message, cause);
	}
}
