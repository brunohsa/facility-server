package br.com.facility.exceptions.handler;

import br.com.facility.exceptions.BaseException;
import br.com.facility.exceptions.ErrorMessages;
import br.com.facility.exceptions.InternalServerErrorException;
import br.com.facility.exceptions.InvalidParameterException;
import br.com.facility.exceptions.NotFoundException;
import br.com.facility.webservice.model.error.ErrorModel;
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
	public ResponseEntity<ErrorModel> facilityAPIErrors(NotFoundException ex) {
		ErrorModel error = new ErrorModel(HttpStatus.NOT_FOUND, ex.getMessage());
		return new ResponseEntity(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({InvalidParameterException.class, IllegalStateException.class})
	public ResponseEntity<Object> invalidParameter(BaseException e) {
		ErrorModel error = new ErrorModel(HttpStatus.BAD_REQUEST, e.getMessage());
		return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InternalServerErrorException.class)
	public ResponseEntity<Object> internalServerError(InternalServerErrorException e) {
		log.error(e.getMessage());

		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		ErrorModel error = new ErrorModel(status, e.getMessage());
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
