package br.com.facility.webservice.model.error;

import br.com.facility.util.JsonUtil;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorModel {

	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime dateTime;

	private String status;

	private String error;

	public ErrorModel(HttpStatus httpStatus, String error) {
		this.dateTime = LocalDateTime.now();
		this.status = httpStatus.toString();
		this.error = error;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public String getStatus() {
		return status;
	}

	public String getError() {
		return error;
	}

	public String toJson() {
		return JsonUtil.convertObjectToJson(this);
	}
}
