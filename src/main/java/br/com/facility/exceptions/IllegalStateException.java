package br.com.facility.exceptions;

public class IllegalStateException extends BaseException {

    public IllegalStateException() {
        super(ErrorMessages.ILLEGAL_STATE_EXCEPTION);
    }
}