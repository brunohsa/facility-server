package br.com.facility.interceptor;

import br.com.facility.json.JsonError;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RequestInterceptor extends HandlerInterceptorAdapter {

	private static final String AUTH = "auth";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
		String auth = request.getHeader(AUTH);
		if (auth == null || auth.isEmpty()) {
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

			loginError(response, "Auth nulo.", "Falha na autenticação do usuário.");

			return false;
		}
		return true;
	}

	private void loginError(HttpServletResponse response, String cause, String description) throws Exception {
		JsonError error = new JsonError(HttpStatus.UNAUTHORIZED, cause, description);
		String errorJson = new ObjectMapper().writeValueAsString(error);
		response.getWriter().write(errorJson);
	}

	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView)
			throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e)
			throws Exception {
	}
}