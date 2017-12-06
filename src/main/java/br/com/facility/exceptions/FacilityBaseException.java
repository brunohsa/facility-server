package br.com.facility.exceptions;

public class FacilityBaseException extends RuntimeException {

	public FacilityBaseException(String message, String cause) {
		super(message, new Throwable(cause));
	}

	public String getCauseMessage(){
		return getCause() != null ? getCause().getMessage() : "";
	}
}
