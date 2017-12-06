package br.com.facility.json.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ChangePasswordRequest {

	private String newPassword;

	private String oldPassword;

	public ChangePasswordRequest() {
	}

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
