package br.com.facility.json.response;

import br.com.facility.model.User;

public class LoginResponse {

	private String token;
	private UserResponse user;

	public LoginResponse(String token, UserResponse user) {
		this.token = token;
		this.user = user;
	}

	public LoginResponse(String token, User user) {
		this.token = token;
		this.user = new UserResponse(user);
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public UserResponse getUser() {
		return user;
	}

	public void setUser(UserResponse user) {
		this.user = user;
	}
}
