package br.com.facility.exceptions.handler;

import br.com.facility.exceptions.webservice.FacilityWSBaseException;
import br.com.facility.json.error.JsonError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
//@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class FacilityWSHandlerException {

	@ExceptionHandler
	public ResponseEntity<Object> handleConflict(FacilityWSBaseException ex) {
		JsonError error = new JsonError(ex.getHttpStatus(), ex.getCauseMessage(), ex.getMessage());
		return new ResponseEntity(error, ex.getHttpStatus());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleConflict(Exception e) throws Exception {
		e.printStackTrace();

		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		JsonError error = new JsonError(status, "Erro Inesperado ! \n Por Favor, Contate um Admistrador.", "Erro Interno");
		return new ResponseEntity(error, status);
	}
}
