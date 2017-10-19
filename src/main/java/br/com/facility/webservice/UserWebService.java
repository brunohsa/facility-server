package br.com.facility.webservice;

import br.com.facility.json.JsonError;
import br.com.facility.json.UserJson;
import br.com.facility.model.User;
import br.com.facility.service.IUserService;
import br.com.facility.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Objects;

@RestController
@RequestMapping("/users")
public class UserWebService {

	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/findbyid/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity findById(@PathVariable("id") Long id) {
		User user = userService.findById(id);
		if (Objects.isNull(user)) {
			JsonError error = new JsonError(HttpStatus.NOT_FOUND, "Não foi possível encontrat o usuário com a identificação " + id, "Usuário inexistente");
			return new ResponseEntity(error, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity insert(@RequestParam("user") String userJson) {
		return save(userJson);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity delete(@PathVariable("id") Long id) {
		userService.delete(id);
		return new ResponseEntity(HttpStatus.OK);
	}

	@RequestMapping(value = "/update/{user}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity update(@PathVariable("user") String userJson) {
		return save(userJson);
	}

	private ResponseEntity save(String userJsonStr) {
		UserJson userJson = null;
		try {
			userJson = JsonUtil.convertJsonToObject(userJsonStr, UserJson.class);
		} catch (IOException e) {
			e.printStackTrace();
			JsonError error = new JsonError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), "Erro a fazer o parse do json.");
			return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		User user = new User(userJson);
		userService.save(user);
		return new ResponseEntity(user, HttpStatus.OK);
	}
}

