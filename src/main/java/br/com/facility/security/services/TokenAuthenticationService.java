package br.com.facility.security.services;

import br.com.facility.exceptions.InternalServerErrorException;
import br.com.facility.exceptions.webservice.ExpiredTokenException;
import br.com.facility.json.response.LoginResponse;
import br.com.facility.model.User;
import br.com.facility.service.UserService;
import br.com.facility.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

@Component
public class TokenAuthenticationService {

    @Autowired
    private UserService userService;

    // EXPIRATION_TIME = 24 horas -> 86400000
    static final long EXPIRATION_TIME = 86400000;
    static final String TOKEN = "token";
    static final String SECRET = "f@cility_";

    public void addAuthentication(HttpServletResponse response, String username) throws IOException, InternalServerErrorException {
        String token = getToken(username);
        String userResponse = getSucessAuthenticationJson(token, username);
        addResponseSucessMessage(response, userResponse);
    }

    private void addResponseSucessMessage(HttpServletResponse response, String responseBody) {
        try {
            response.getWriter().write(responseBody);
            response.setStatus(HttpStatus.OK.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        } catch (IOException e) {
            e.printStackTrace();
            throw new InternalServerErrorException("IO error");
        }
    }

    private String getToken(String username) {
        Date expitationDate = getDateExpirationLogin();

        String token = Jwts.builder()
                .setSubject(username)
                .setExpiration(expitationDate)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();

        return token;
    }

    private Date getDateExpirationLogin() {
        return new Date(System.currentTimeMillis() + EXPIRATION_TIME);
    }

    private String getSucessAuthenticationJson(String token, String username) throws InternalServerErrorException {
        User user = userService.findByUserName(username);
        try {
            return JsonUtil.convertObjectToJson(new LoginResponse(token, user));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new InternalServerErrorException("erro ao converter");
        }
    }

    public Authentication getAuthentication(HttpServletRequest request) throws ExpiredJwtException, ServletException {
        Optional<String> tokenOptional = Optional.ofNullable(request.getHeader(TOKEN));
        if (!tokenOptional.isPresent()) {
            return null;
        }
        String token = tokenOptional.get();

        String user;
        try {
            user = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody().getSubject();
        } catch (ExpiredJwtException e) {
            throw new ExpiredTokenException();
        }
        return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
    }
}
