package br.com.facility.service;

import br.com.facility.model.User;
import br.com.facility.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService extends GenericService<User, UserRepository> {

	@Autowired
	private UserRepository userRepository;

	public User findByUserName(String username) {
		User user = userRepository.findByUserName(username);
		if (Objects.isNull(user)) {
			//throw exception
		}
		return user;
	}
}
