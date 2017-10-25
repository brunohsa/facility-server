package br.com.facility.json.response.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseError {

	public static ResponseEntity badRequest(String message, String cause) {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		return newResponseEntityWithJsonErrorBody(httpStatus, message, cause);
	}

	public static ResponseEntity internalServerError(String message, String cause) {
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		return newResponseEntityWithJsonErrorBody(httpStatus, message, cause);
	}

	public static ResponseEntity notFound(String message, String cause) {
		HttpStatus httpStatus = HttpStatus.NOT_FOUND;
		return newResponseEntityWithJsonErrorBody(httpStatus, message, cause);
	}

	public static ResponseEntity unauthorized(String message, String cause) {
		HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
		return newResponseEntityWithJsonErrorBody(httpStatus, message, cause);
	}

	private static ResponseEntity<JsonError> newResponseEntityWithJsonErrorBody(HttpStatus httpStatus, String message, String cause) {
		JsonError error = new JsonError(httpStatus, message, cause);
		return new ResponseEntity(error, httpStatus);
	}
}
