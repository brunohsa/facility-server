package br.com.facility.webservice;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserWS {

	@RequestMapping(value = "/example", method = RequestMethod.GET)
	public String exampleGet() {

		return "call sucessful";
	}
}
