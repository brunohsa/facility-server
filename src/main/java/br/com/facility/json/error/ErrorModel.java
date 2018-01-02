package br.com.facility.json.error;

import br.com.facility.util.JsonUtil;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorModel {

	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private final LocalDateTime dateTime;

	private final String status;

	private final String error;

	private final String cause;

	public ErrorModel(HttpStatus httpStatus, String cause) {
		this.dateTime = LocalDateTime.now();
		this.status = httpStatus.toString();
		this.error = httpStatus.getReasonPhrase();
		this.cause = cause;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public String getStatus() {
		return status;
	}

	public String getCause() {
		return cause;
	}

	public String getError() {
		return error;
	}

	public String toJson() {
		return JsonUtil.convertObjectToJson(this);
	}
}
