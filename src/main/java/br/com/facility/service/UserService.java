package br.com.facility.service;

import br.com.facility.exceptions.ws.UserNotFoundException;
import br.com.facility.model.User;
import br.com.facility.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User findByUserName(String username) {
		Optional<User> user = userRepository.findByUsername(username);
		return user.orElseThrow(() -> new UserNotFoundException(username));
	}

	public void delete() {
		userRepository.deleteByUsername(getLoggedUsername());
	}

	public User save(User user) {
		return userRepository.save(user);
	}

	public User findLoggedUser() {
		return findByUserName(getLoggedUsername());
	}

	public String getLoggedUsername() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.getName();
	}
}
