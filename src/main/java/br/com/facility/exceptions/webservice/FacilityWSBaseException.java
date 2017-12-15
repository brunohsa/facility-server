package br.com.facility.exceptions.webservice;

import br.com.facility.util.Messages;
import org.springframework.http.HttpStatus;

public class FacilityWSBaseException extends RuntimeException {

	private HttpStatus httpStatus;

	public FacilityWSBaseException(HttpStatus httpStatus, String message, String cause) {
		super(Messages.getMessage(message), new Throwable(cause));
		this.httpStatus = httpStatus;
	}

	public String getCauseMessage() {
		return getCause() != null ? getCause().getMessage() : "";
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}
