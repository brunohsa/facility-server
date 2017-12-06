package br.com.facility.exceptions;

public class DataNotFoundException extends FacilityBaseException {

	public DataNotFoundException() {
		super("message", "cause");
	}
}
