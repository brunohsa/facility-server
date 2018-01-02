package br.com.facility.json.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ChangePasswordRequest {

	private final String newPassword;

	private final String oldPassword;

	public ChangePasswordRequest(String newPassword, String oldPassword) {
		this.newPassword = newPassword;
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public String getOldPassword() {
		return oldPassword;
	}
}
