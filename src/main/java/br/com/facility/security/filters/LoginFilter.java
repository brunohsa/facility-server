package br.com.facility.security.filters;

import br.com.facility.json.error.JsonError;
import br.com.facility.json.request.LoginRequest;
import br.com.facility.security.services.TokenAuthenticationService;
import br.com.facility.util.Messages;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class LoginFilter extends AbstractAuthenticationProcessingFilter {

    private static final String LOGIN_PATH = "/login";

    @Autowired
    private TokenAuthenticationService authenticationService;

    public LoginFilter() {
        super(new AntPathRequestMatcher(LOGIN_PATH));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {

        LoginRequest login = getLoginRequest(httpServletRequest.getInputStream());
        UsernamePasswordAuthenticationToken userAuthenticator = getUserAuthenticate(login);
        try {
            return getAuthenticationManager().authenticate(userAuthenticator);
        } catch (BadCredentialsException e) {
            setAuthenticationErrorResponse(response, HttpStatus.UNAUTHORIZED, "login.wrong_user_or_password", "login.login_error");
            return null;
        }
    }

    private void setAuthenticationErrorResponse(HttpServletResponse response, HttpStatus httpStatus, String cause, String description) throws IOException {
        JsonError error = new JsonError(httpStatus, getMessage(cause), getMessage(description));

        response.getWriter().write(error.toJson());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(httpStatus.value());
    }

    private LoginRequest getLoginRequest(ServletInputStream inputStream) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(inputStream, LoginRequest.class);
    }

    private String getMessage(String key) {
        return Messages.getMessage(key);
    }

    private UsernamePasswordAuthenticationToken getUserAuthenticate(LoginRequest login) {
        return new UsernamePasswordAuthenticationToken(login.getUserName(), login.getPassword(), Collections.emptyList());
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain,
            Authentication auth) throws IOException, ServletException {
        authenticationService.addAuthentication(response, auth.getName());
    }
}
