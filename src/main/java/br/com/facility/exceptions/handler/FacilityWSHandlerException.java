package br.com.facility.exceptions.handler;

import br.com.facility.exceptions.ErrorMessages;
import br.com.facility.exceptions.InternalServerErrorException;
import br.com.facility.exceptions.ws.NotFoundException;
import br.com.facility.json.error.ErrorModel;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@ControllerAdvice
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class FacilityWSHandlerException {

	static Logger log = Logger.getLogger(FacilityWSHandlerException.class);

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Object> facilityAPIErrors(NotFoundException ex) {
		ErrorModel error = new ErrorModel(ex.getHttpStatus(), ex.getMessage());
		return new ResponseEntity(error, ex.getHttpStatus());
	}

	@ExceptionHandler(InternalServerErrorException.class)
	public ResponseEntity<Object> internalServerError(InternalServerErrorException e) {
		log.error(e.getMessage());

		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		ErrorModel error = new ErrorModel(status, ErrorMessages.INTERNAL_SERVER_ERROR.getText("102515"));
		return new ResponseEntity(error, status);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> unexpectedError(Exception e) {
		log.error(e.getMessage());

		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		ErrorModel error = new ErrorModel(status, ErrorMessages.UNEXPECTED_ERROR.getText());
		return new ResponseEntity(error, status);
	}
}
