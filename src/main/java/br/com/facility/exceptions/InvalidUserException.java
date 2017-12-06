package br.com.facility.exceptions;

public class InvalidUserException extends FacilityBaseException {

    public InvalidUserException(String message, String cause) {
        super(message, cause);
    }
}
