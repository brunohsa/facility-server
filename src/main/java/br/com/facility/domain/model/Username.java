package br.com.facility.domain.model;

import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

public class Username {

	private static final Pattern REGEX_USER = Pattern.compile("([A-Za-z0-9\\-_]+)", Pattern.CASE_INSENSITIVE);

	private final String username;

	public Username(String username) {
		this.validate(username);
		this.username = username;
	}

	private void validate(String username) {
		if (StringUtils.isEmpty(username)) {
			throw new RuntimeException();
		}
		if (!usernameIsValid(username)) {
			throw new RuntimeException();
		}
	}

	private boolean usernameIsValid(String username) {
		return REGEX_USER.matcher(username).matches();
	}

	public String getUsername() {
		return username;
	}
}
