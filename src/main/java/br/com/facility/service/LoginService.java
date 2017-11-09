package br.com.facility.service;

import br.com.facility.exceptions.InvalidLoginException;
import br.com.facility.exceptions.InvalidTokenException;
import br.com.facility.model.User;
import br.com.facility.repository.UserRepository;
import br.com.facility.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Service
public class LoginService {

	@Autowired
	private UserRepository userRepository;

	public User doLogin(String userName, String password) throws InvalidLoginException {
		User user = userRepository.getByUserNameAndPassword(userName, password);
		if (Objects.isNull(user)) {
			throw new InvalidLoginException("login.webservice.wrong_user_or_password", "login.webservice.login_error");
		}
		updateLoginDatasUser(user);
		return user;
	}

	public void validateToken(String token) throws InvalidTokenException {
		if (Objects.isNull(token) || token.isEmpty()) {
			throw new InvalidTokenException("Token vazio, para fazer requisições é necessário informar o token do usuário.", "Falha na autenticação do usuário.");
		}
		boolean tokenValido = userRepository.existsByToken(token);
		if (!tokenValido) {
			throw new InvalidTokenException("Token inválido", "Falha na autenticação do usuário.");
		}
	}

	private void updateLoginDatasUser(User user) {
		String token = generateToken(user.getUserName());
		user.setToken(token);
		user.setLastLogin(LocalDateTime.now());
		userRepository.save(user);
	}

	private String generateToken(String username) {
		String uuid = UUID.randomUUID().toString().replace("-", "");
		String date = DateUtil.formattNanoSecond(LocalDateTime.now());

		return username.concat("|").concat(uuid).concat(date);
	}
}
