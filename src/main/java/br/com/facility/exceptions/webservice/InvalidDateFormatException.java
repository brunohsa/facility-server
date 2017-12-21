package br.com.facility.exceptions.webservice;

import br.com.facility.exceptions.ErrorMessages;
import org.springframework.http.HttpStatus;

public class InvalidDateFormatException extends FacilityWSBaseException {

	public InvalidDateFormatException() {
		super(HttpStatus.BAD_REQUEST, ErrorMessages.INVALID_DATE_FORMAT.getText());
	}
}
