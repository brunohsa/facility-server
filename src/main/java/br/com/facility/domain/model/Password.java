package br.com.facility.domain.model;

import org.springframework.util.StringUtils;

public class Password {

	private final String password;

	public Password(String password) {
		this.validate(password);
		this.password = password;
	}

	private void validate(String password) {
		if(StringUtils.isEmpty(password)) {
			throw new RuntimeException("Password nulo !");
		}
	}
	public String getPassword() {
		return password;
	}
}
