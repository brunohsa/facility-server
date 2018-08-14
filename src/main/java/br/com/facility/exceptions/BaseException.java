package br.com.facility.exceptions;

public class BaseException extends RuntimeException {

    public BaseException(ErrorMessages error) {
        super(error.getText());
    }
}
