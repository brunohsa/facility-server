package br.com.facility.exceptions.handlers;

import br.com.facility.exceptions.webservice.FacilityWSBaseException;
import br.com.facility.json.error.ErrorModel;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@ControllerAdvice
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class FacilityWSHandlerException {

	@ExceptionHandler
	public ResponseEntity<Object> handleConflict(FacilityWSBaseException ex) {
		ErrorModel error = new ErrorModel(ex.getHttpStatus(), ex.getCauseMessage(), ex.getMessage());
		return new ResponseEntity(error, ex.getHttpStatus());
	}

	@ExceptionHandler
	public ResponseEntity<Object> handleConflict(Exception e) throws Exception {
		e.printStackTrace();

		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		ErrorModel error = new ErrorModel(status, "Erro Inesperado ! \n Por Favor, Contate um Admistrador.", "Erro Interno");
		return new ResponseEntity(error, status);
	}
}
