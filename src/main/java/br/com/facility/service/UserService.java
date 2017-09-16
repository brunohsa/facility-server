package br.com.facility.service;


import br.com.facility.model.User;
import br.com.facility.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public void insert(User user) {
		userRepository.save(user);
	}

	public User getById(Long id){
		return userRepository.findOne(id);
	}

	public List<User> getAll(){
		return (List<User>) userRepository.findAll();
	}
}
