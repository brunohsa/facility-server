package br.com.facility.exceptions.ws;

import br.com.facility.exceptions.ErrorMessages;
import org.springframework.http.HttpStatus;

public class ExpiredTokenException extends NotFoundException {

	public ExpiredTokenException() {
		super(HttpStatus.BAD_REQUEST, ErrorMessages.EXPIRED_SESSION.getText());
	}
}
