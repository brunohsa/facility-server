package br.com.facility.exceptions.ws;

import br.com.facility.exceptions.ErrorMessages;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends NotFoundException {

    public UserNotFoundException(String identifier) {
        super(HttpStatus.NOT_FOUND, ErrorMessages.USER_NOT_FOUND.getText(identifier));
    }
}
