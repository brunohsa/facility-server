package br.com.facility.exceptions.webservice;

import org.springframework.http.HttpStatus;

public class InvalidUserException extends FacilityWSBaseException {

    public InvalidUserException() {
        super(HttpStatus.NOT_FOUND, "User not found", "Invalid user");
    }
}
