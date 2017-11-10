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
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws AuthenticationException, IOException, ServletException {

        LoginRequest login = new ObjectMapper()
                .readValue(httpServletRequest.getInputStream(), LoginRequest.class);

        UsernamePasswordAuthenticationToken userAuthenticator = new UsernamePasswordAuthenticationToken(login.getUserName(), login.getPassword(), Collections.emptyList());
        return getAuthenticationManager().authenticate(userAuthenticator);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain,
            Authentication auth) throws IOException, ServletException {

        authenticationService.addAuthentication(response, auth.getName());
    }
}
