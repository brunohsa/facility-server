package br.com.facility.exceptions.ws;

import br.com.facility.exceptions.ErrorMessages;
import org.springframework.http.HttpStatus;

public class FinanceNotFoundException extends NotFoundException {

	public FinanceNotFoundException(Long identifier) {
		super(HttpStatus.NOT_FOUND, ErrorMessages.FINANCE_NOT_FOUND.getText(identifier));
	}
}
