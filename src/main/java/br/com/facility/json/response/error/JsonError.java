package br.com.facility.json.response.error;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class JsonError {

	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime dateTime;

	private String httpStatus;

	private String typeHttpStatus;

	private String cause;

	private String description;

	public JsonError() {
	}

	public JsonError(HttpStatus httpStatus, String cause, String description) {
		this.dateTime = LocalDateTime.now();
		this.httpStatus = httpStatus.toString();
		this.typeHttpStatus = httpStatus.getReasonPhrase();
		this.cause = cause;
		this.description = description;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public String getHttpStatus() {
		return httpStatus;
	}

	public String getCause() {
		return cause;
	}

	public String getDescription() {
		return description;
	}

	public String getTypeHttpStatus() {
		return typeHttpStatus;
	}
}
