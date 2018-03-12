package br.com.facility.webservice.model.response;

import br.com.facility.model.User;

public class LoginResponse {

	private String token;
	private UserResponse user;

	public LoginResponse() {
	}

	public LoginResponse(String token, User user) {
		this.token = token;
		this.user = new UserResponse(user);
	}

	public String getToken() {
		return token;
	}

	public UserResponse getUser() {
		return user;
	}
}
