package br.com.facility.exceptions;

import br.com.facility.util.Messages;

public class FacilityBaseException extends RuntimeException {

	public FacilityBaseException(String message, String cause) {
		super(Messages.getMessage(message), new Throwable(cause));
	}

	public String getCauseMessage(){
		return getCause() != null ? getCause().getMessage() : "";
	}
}
