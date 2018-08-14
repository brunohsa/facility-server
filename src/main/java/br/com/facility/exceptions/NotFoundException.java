package br.com.facility.exceptions;

public class NotFoundException extends BaseException {

	public NotFoundException(ErrorMessages error) {
		super(error);
	}
}
