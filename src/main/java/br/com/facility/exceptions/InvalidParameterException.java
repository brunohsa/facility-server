package br.com.facility.exceptions;

public class InvalidParameterException extends BaseException {

    public InvalidParameterException(ErrorMessages error) {
        super(error);
    }

    public InvalidParameterException(String message) {
        super(message);
    }
}
