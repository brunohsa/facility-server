package br.com.facility.webservice;

import br.com.facility.model.User;
import br.com.facility.service.UserService;
import br.com.facility.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserWS {

    @Autowired
    private UserService service;

    @RequestMapping(value = "/example", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String exampleGet() {

        JsonUtil converter = new JsonUtil();

        User user = new User("Bruno", "Araujo", "bhdos", "abcd1234", "brunohsa@hotmail.com");
        String classe = converter.convertObjectToJson(user);

        User teste = converter.convertJsonToObject(classe, User.class);

        return converter.convertObjectToJson(teste);
    }
}
