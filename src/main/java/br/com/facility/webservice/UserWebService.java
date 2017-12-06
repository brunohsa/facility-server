package br.com.facility.webservice;

import br.com.facility.exceptions.InvalidUserException;
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
import java.util.Optional;

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
        User user = null;
        try {
            user = userService.findByUserName(username);
        } catch (InvalidUserException e) {
            ResponseEntity error = ResponseError.notFound(e.getMessage(), e.getCauseMessage());
            return error;
        }
        return new ResponseEntity(new UserResponse(user), HttpStatus.OK);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity insert(@RequestBody UserRequest userJson) {
        return save(userJson);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity delete() {
        incomeService.deleteAll();
        expenseService.deleteAll();
        userService.delete();
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity update(@RequestBody UserRequest userJson) {
        User user = userService.findLoggedUser();
        user.setEmail(Optional.ofNullable(userJson.getEmail()).orElse(user.getEmail()));
        user.setName(Optional.ofNullable(userJson.getName()).orElse(user.getName()));
        user.setLastName(Optional.ofNullable(userJson.getLastName()).orElse(user.getLastName()));
        user.setPassword(Optional.ofNullable(userJson.getPassword()).orElse(user.getPassword()));
        user = userService.save(user);
        return new ResponseEntity(new UserResponse(user), HttpStatus.OK);
    }

    private ResponseEntity save(UserRequest userJson) {
        User user = new User(userJson);
        user = userService.save(user);
        return new ResponseEntity(new UserResponse(user), HttpStatus.OK);
    }
}

