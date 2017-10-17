package br.com.facility.model;

import br.com.facility.util.DateUtil;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class JsonError {

	private String dateTime;

	private String httpStatus;

	private String typeHttpStatus;

	private String cause;

	private String description;

	public JsonError() {
	}

	public JsonError(HttpStatus httpStatus, String cause, String description) {
		this.dateTime = DateUtil.formattDateTime(LocalDateTime.now());
		this.httpStatus = httpStatus.toString();
		this.typeHttpStatus = httpStatus.getReasonPhrase();
		this.cause = cause;
		this.description = description;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
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
