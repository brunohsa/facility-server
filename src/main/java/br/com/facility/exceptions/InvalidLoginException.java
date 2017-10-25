package br.com.facility.exceptions;

import br.com.facility.util.Messages;

public class InvalidLoginException extends FacilityBaseException {

	public InvalidLoginException(String message, String cause) {
		super(Messages.getMessage(message), Messages.getMessage(cause));
	}
}
