package br.com.facility.webservice;

import br.com.facility.facade.IUserFacade;
import br.com.facility.facade.UserFacade;
import br.com.facility.model.User;
import br.com.facility.service.ExpenseService;
import br.com.facility.service.IncomeService;
import br.com.facility.service.UserService;
import br.com.facility.webservice.model.request.UserRequest;
import br.com.facility.webservice.model.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserWebService {


    @Autowired
    private IUserFacade userFacade;

    @RequestMapping(value = "/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity findByUsername(@PathVariable("username") String username) {
        User user = userFacade.findByUserName(username);
        return new ResponseEntity(new UserResponse(user), HttpStatus.OK);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity insert(@RequestBody UserRequest userJson) {
        UserResponse response = userFacade.save(userJson);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity delete() {
        userFacade.delete();
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity update(@RequestBody UserRequest userJson) {
        User user = null;
//        User user = userService.findLoggedUser();
//        user.setEmail(Optional.ofNullable(userJson.getEmail()).orElse(user.getEmail()));
//        user.setName(Optional.ofNullable(userJson.getName()).orElse(user.getName()));
//        user.setLastName(Optional.ofNullable(userJson.getLastName()).orElse(user.getLastName()));
//
//        user = userService.save(user);

        return new ResponseEntity(new UserResponse(user), HttpStatus.OK);
    }
}

