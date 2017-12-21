package br.com.facility.exceptions;

import br.com.facility.util.Messages;

import java.text.MessageFormat;

public enum ErrorMessages {

	WRONG_USER_OR_PASSWORD("wrong_user_or_password"),
	ACCESS_NOT_ALLOWED("access_not_allowed"),
	UNEXPECTED_ERROR("unexpected_error"),
	EXPIRED_SESSION("expired_session"),
	FINANCE_NOT_FOUND("finance_not_found"),
	INVALID_DATE_FORMAT("invalid_date_format"),
	USER_NOT_FOUND("user_not_found"),
	INTERNAL_SERVER_ERROR("internal_server_error");

	private String name;

	ErrorMessages(String name) {
		this.name = name;
	}

	public String getText() {
		return Messages.getMessage(name);
	}

	public String getText(Object... parameters) {
		String text = getText();
		return MessageFormat.format(text, parameters);
	}
}
