package br.com.facility.exceptions;

public class FinanceNotFoundException extends NotFoundException {

	public FinanceNotFoundException() {
		super(ErrorMessages.FINANCE_NOT_FOUND);
	}
}
