package br.com.facility.exceptions;

public class InvalidDateFormatException extends NotFoundException {

	public InvalidDateFormatException() {
		super(ErrorMessages.INVALID_DATE_FORMAT);
	}
}
