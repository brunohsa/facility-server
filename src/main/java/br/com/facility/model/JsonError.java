package br.com.facility.model;

import java.time.LocalDateTime;

public class JsonError {

	private LocalDateTime dateTime;

	private String httpStatus;

	private String cause;

	private String description;

	public JsonError() {
	}

	public JsonError(LocalDateTime dateTime, String httpStatus, String cause, String description) {
		this.dateTime = dateTime;
		this.httpStatus = httpStatus;
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
}
