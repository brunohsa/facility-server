package br.com.facility.webservice;

import br.com.facility.model.User;
import br.com.facility.service.IUserService;
import br.com.facility.service.UserService;
import br.com.facility.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping(value = "/user")
public class UserWebService {

	@Autowired
	private IUserService userService;

	@RequestMapping(value = "findbyid", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity findById(@RequestParam(value = "id") Long id) {
		User user = userService.findById(id);
		if(Objects.isNull(user)) {
			return new ResponseEntity("Não Encontrado",HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity insert(@RequestParam(value = "user") String userJson) {
		return save(userJson);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity delete(@RequestParam(value = "id") Long id) {
		userService.delete(id);
		return new ResponseEntity(HttpStatus.OK);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity update(@RequestParam(value = "user") String userJson) {
		return save(userJson);
	}

	private ResponseEntity save(String userJson) {
		User user = JsonUtil.convertJsonToObject(userJson, User.class);
		if (Objects.isNull(user)) {
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		userService.save(user);
		return new ResponseEntity(user, HttpStatus.OK);
	}
}

