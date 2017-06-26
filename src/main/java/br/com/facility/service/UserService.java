package br.com.facility.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.facility.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
}
