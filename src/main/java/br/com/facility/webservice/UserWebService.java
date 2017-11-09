package br.com.facility.webservice;

import br.com.facility.json.request.UserRequest;
import br.com.facility.json.response.UserResponse;
import br.com.facility.json.response.error.ResponseError;
import br.com.facility.model.User;
import br.com.facility.service.ExpenseService;
import br.com.facility.service.IncomeService;
import br.com.facility.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/users")
public class UserWebService {

    @Autowired
    private UserService userService;

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private IncomeService incomeService;

    @RequestMapping(value = "/find/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity findByUsername(@PathVariable("username") String username) {
        User user = userService.findByUserName(username);
        if (Objects.isNull(user)) {
            ResponseEntity error = ResponseError.notFound("Não foi possível encontrat o usuário " + username, "Usuário inexistente");
            return error;
        }
        return new ResponseEntity(new UserResponse(user), HttpStatus.OK);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity insert(@RequestBody UserRequest userJson) {
        return save(userJson);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity delete(@PathVariable("id") Long id, @RequestHeader("token") String token) {
        incomeService.delete(id);
        expenseService.delete(id);
        userService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity update(@RequestBody UserRequest userJson) {
        return save(userJson);
    }

    private ResponseEntity save(UserRequest userJson) {
        User user = new User(userJson);
        User newUser = userService.save(user);
        return new ResponseEntity(new UserResponse(newUser), HttpStatus.OK);
    }
}

