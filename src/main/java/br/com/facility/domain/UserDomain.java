package br.com.facility.domain;

import br.com.facility.domain.model.Password;
import br.com.facility.domain.model.Username;

import static java.util.Optional.ofNullable;

public class UserDomain {

	private Username username;

	private Password password;

	public UserDomain(Username username, Password password) {
		this.username = ofNullable(username).orElseThrow(RuntimeException::new);
		this.password = ofNullable(password).orElseThrow(RuntimeException::new);
	}

	public Username getUsername() {
		return username;
	}

	public Password getPassword() {
		return password;
	}
}
