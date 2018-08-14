package br.com.facility.exceptions;

public class UserNotFoundException extends NotFoundException {

    public UserNotFoundException() {
        super(ErrorMessages.USER_NOT_FOUND);
    }
}
