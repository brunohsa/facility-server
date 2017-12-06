package br.com.facility.exceptions;

public class InternalErrorException extends FacilityBaseException {

	public InternalErrorException(String message, String cause) {
		super(message, cause);
	}
}
