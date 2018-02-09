package br.com.facility.exceptions.ws;

import br.com.facility.exceptions.ErrorMessages;
import org.springframework.http.HttpStatus;

public class InvalidDateFormatException extends NotFoundException {

	public InvalidDateFormatException() {
		super(HttpStatus.BAD_REQUEST, ErrorMessages.INVALID_DATE_FORMAT.getText());
	}
}
