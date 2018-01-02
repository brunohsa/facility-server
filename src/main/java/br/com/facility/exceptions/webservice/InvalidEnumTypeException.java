package br.com.facility.exceptions.webservice;

import br.com.facility.exceptions.ErrorMessages;
import org.springframework.http.HttpStatus;

public class InvalidEnumTypeException extends FacilityWSBaseException {

	public InvalidEnumTypeException(String enumName) {
		super(HttpStatus.BAD_REQUEST, ErrorMessages.INVALID_ENUM_TYPE.getText(enumName));
	}
}
