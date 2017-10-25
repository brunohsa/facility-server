package br.com.facility.webservice;

import br.com.facility.exceptions.InvalidLoginException;
import br.com.facility.json.response.error.JsonError;
import br.com.facility.json.request.LoginRequest;
import br.com.facility.json.response.LoginResponse;
import br.com.facility.json.response.UserResponse;
import br.com.facility.json.response.error.ResponseError;
import br.com.facility.model.User;
import br.com.facility.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginWebService {

	@Autowired
	private IUserService userService;

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity logar(@RequestBody LoginRequest login) {
		User user = null;
		try {
			user = userService.doLogin(login.getUserName(), login.getPassword());
		} catch (InvalidLoginException e) {
			return ResponseError.unauthorized(e.getMessage(), e.getCauseMessage());
		}
		LoginResponse loginResponse = new LoginResponse(user.getToken(), new UserResponse(user));
		return ResponseEntity.ok(loginResponse);
	}
}
