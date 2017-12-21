package br.com.facility.exceptions.webservice;

import br.com.facility.exceptions.ErrorMessages;
import org.springframework.http.HttpStatus;

public class ExpiredTokenException extends FacilityWSBaseException {

	public ExpiredTokenException() {
		super(HttpStatus.BAD_REQUEST, ErrorMessages.EXPIRED_SESSION.getText());
	}
}
