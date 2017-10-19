package br.com.facility.webservice;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/login")
public class LoginWebService {

	private void logar(){

		UUID token = UUID.randomUUID();
	}
}
