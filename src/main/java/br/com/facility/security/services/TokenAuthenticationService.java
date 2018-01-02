package br.com.facility.security.services;

import br.com.facility.exceptions.InternalServerErrorException;
import br.com.facility.exceptions.webservice.ExpiredTokenException;
import br.com.facility.json.response.LoginResponse;
import br.com.facility.json.response.UserResponse;
import br.com.facility.model.User;
import br.com.facility.service.UserService;
import br.com.facility.util.JsonUtil;
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
            throw new InternalServerErrorException(e.getMessage());
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
        UserResponse userResponse = new UserResponse(user);

        return JsonUtil.convertObjectToJson(new LoginResponse(token, userResponse));
    }

    public Authentication getAuthentication(HttpServletRequest request) throws ExpiredJwtException, ServletException {
        Optional<String> token = getHeaderToken(request);
        if (!token.isPresent()) {
            return null;
        }
        String user = getUsername(token.get());
        return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
    }

    private Optional<String> getHeaderToken(HttpServletRequest request) {
        String token = request.getHeader(TOKEN);
        return Optional.ofNullable(token);
    }

    private String getUsername(String token) {
        Claims claims = getClaims(token);
        return claims.getSubject();
    }

    private Claims getClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            throw new ExpiredTokenException();
        }
    }
}
