package br.com.facility.exceptions.handler;

import br.com.facility.json.error.JsonError;
import br.com.facility.util.Messages;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class FailureAuthenticationHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
			throws IOException, ServletException {

		String error = new JsonError(HttpStatus.UNAUTHORIZED, getMessage("login.wrong_user_or_password"), getMessage("login.login_error")).toJson();
		response.getWriter().write(error);

		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
	}

	private String getMessage(String key) {
		return Messages.getMessage(key);
	}
}
