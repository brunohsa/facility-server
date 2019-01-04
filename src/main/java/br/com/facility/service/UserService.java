package br.com.facility.service;

import br.com.facility.model.User;
import br.com.facility.repository.UserRepository;
import br.com.facility.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User findByUserName(String username) {
		if(StringUtils.isEmpty(username)) {
			throw new UserNotFoundException();
		}
		Optional<User> user = userRepository.findByUsername(username);
		return user.orElseThrow(() -> new UserNotFoundException());
	}

	public void delete() {
		userRepository.deleteByUsername(getLoggedUsername());
	}

	public User save(User user) {
		if(StringUtils.isEmpty(user.getName())) {
			throw new RuntimeException("Nome inválido");
		}
		if(StringUtils.isEmpty(user.getUsername())) {
			throw new RuntimeException("Username inválido");
		}
		if(StringUtils.isEmpty(user.getPassword())) {
			throw new RuntimeException("Senha inválida");
		}
		if(StringUtils.isEmpty(user.getEmail())) {
			throw new RuntimeException("Email inválido");
		}
		return userRepository.save(user);
	}

	public User findLoggedUser() {
		return userRepository.findByUsername(getLoggedUsername()).get();
	}

	public String getLoggedUsername() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.getName();
	}
}
