package br.com.facility.security.filters;

import br.com.facility.exceptions.webservice.ExpiredTokenException;
import br.com.facility.exceptions.webservice.InvalidTokenException;
import br.com.facility.json.error.JsonError;
import br.com.facility.security.services.TokenAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTAuthenticationFilter extends GenericFilterBean {

	@Autowired
	private TokenAuthenticationService authenticationService;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		try {
			Authentication authentication = authenticationService.getAuthentication((HttpServletRequest) request);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			filterChain.doFilter(request, response);
		} catch (ExpiredTokenException e) {
			setJWTErrorResponse(response, HttpStatus.BAD_REQUEST, e.getCauseMessage(), e.getMessage());
		} catch (InvalidTokenException e) {
			setJWTErrorResponse(response, HttpStatus.UNAUTHORIZED, e.getCauseMessage(), e.getMessage());
		}
	}

	private void setJWTErrorResponse(ServletResponse response, HttpStatus httpStatus, String cause, String description) throws IOException {
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		JsonError error = new JsonError(httpStatus, cause, description);

		httpResponse.getWriter().write(error.toJson());
		httpResponse.setStatus(httpStatus.value());
		httpResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
	}
}
