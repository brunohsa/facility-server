package br.com.facility.security.services;

import br.com.facility.json.response.LoginResponse;
import br.com.facility.model.User;
import br.com.facility.service.UserService;
import br.com.facility.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;

@Component
public class TokenAuthenticationService {

    @Autowired
    private UserService userService;

    // EXPIRATION_TIME = 1 minuto
    static final long EXPIRATION_TIME = 60000;
    static final String TOKEN = "token";
    static final String SECRET = "f@cility_";

    public void addAuthentication(HttpServletResponse response, String username) {
        String token = getToken(username);
        String userResponse = getSucessAuthenticationJson(token, username);
        try {
            response.setStatus(HttpStatus.OK.value());
            response.getWriter().write(userResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getToken(String username) {
        Date expitationDate = new Date(System.currentTimeMillis() + EXPIRATION_TIME);

        String token = Jwts.builder().setSubject(username).setExpiration(expitationDate)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();

        return token;
    }

    private String getSucessAuthenticationJson(String token, String username) {
        User user = userService.findByUserName(username);
        if (user == null) {
            return "";
        }
        String jsonMessage = "";
        try {
            jsonMessage = JsonUtil.convertObjectToJson(new LoginResponse(token, user));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonMessage;
    }

    public Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(TOKEN);

        if (token != null) {
            // faz parse do token
            String user = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody().getSubject();
            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
            }
        }
        return null;
    }
}
