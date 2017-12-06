package br.com.facility.exceptions;

public class InvalidUserException extends FacilityBaseException {

    public InvalidUserException() {
        super("User not found", "Invalid user");
    }
}
