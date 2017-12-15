package br.com.facility.security.filters;

import br.com.facility.json.request.LoginRequest;
import br.com.facility.security.services.TokenAuthenticationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
        return getAuthenticationManager().authenticate(userAuthenticator);
    }

    private LoginRequest getLoginRequest(ServletInputStream inputStream) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(inputStream, LoginRequest.class);
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
