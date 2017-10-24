package br.com.facility.json.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginRequest {

	private String userName;

	private String password;

	public String getUserName() {
		return Objects.nonNull(userName) ? removeSpaces(userName) : userName;
	}

	public void setUserName(String userName) {
		this.userName = removeSpaces(userName);
	}

	public String getPassword() {
		return Objects.nonNull(password) ? removeSpaces(password) : userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private String removeSpaces(String string) {
		if (Objects.isNull(string) || string.isEmpty()) {
			return "";
		}
		return string.replace(" ", "");
	}
}
