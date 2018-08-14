package br.com.facility.exceptions;

public class ExpiredTokenException extends NotFoundException {

	public ExpiredTokenException() {
		super(ErrorMessages.EXPIRED_SESSION);
	}
}
