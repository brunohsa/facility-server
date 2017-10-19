package br.com.facility.json;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class JsonError {

	@JsonSerialize(using = LocalDateSerializer.class)
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

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public String getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(String httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTypeHttpStatus() {
		return typeHttpStatus;
	}

	public void setTypeHttpStatus(String typeHttpStatus) {
		this.typeHttpStatus = typeHttpStatus;
	}
}
