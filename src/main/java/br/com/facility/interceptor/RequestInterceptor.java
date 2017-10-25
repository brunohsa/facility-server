package br.com.facility.interceptor;

import br.com.facility.exceptions.InvalidTokenException;
import br.com.facility.json.response.error.JsonError;
import br.com.facility.json.response.error.ResponseError;
import br.com.facility.service.IUserService;
import br.com.facility.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
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

	private static final String TOKEN = "token";

	@Autowired
	private IUserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) {
		String auth = request.getHeader(TOKEN);
		try {
			userService.validateUser(auth);
		} catch (InvalidTokenException e) {
			registerLoginError(response, e.getCauseMessage(), e.getMessage());
			return false;
		}
		return true;
	}

	private void registerLoginError(HttpServletResponse response, String cause, String description){
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

		JsonError error = new JsonError(HttpStatus.UNAUTHORIZED, cause, description);
		String errorJson = null;
		try {
			errorJson = JsonUtil.convertObjectToJson(error);
			response.getWriter().write(errorJson);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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