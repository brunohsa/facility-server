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
public class UserService extends GenericService<User, UserRepository> implements IUserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User doLogin(String userName, String password) throws InvalidLoginException {
		User user = userRepository.getByUserNameAndPassword(userName, password);
		if (user == null) {
			throw new InvalidLoginException("login.webservice.wrong_user_or_password", "login.webservice.login_error");
		}
		updateDatasUserLogin(user);
		return user;
	}

	@Override
	public void validateToken(String token) throws InvalidTokenException {
		if(token == null || token.isEmpty()){
			throw new InvalidTokenException("Token vazio, para fazer requisições é necessário informar o token do usuário.", "Falha na autenticação do usuário.");
		}
		User user = userRepository.getByToken(token);
		if (user == null) {
			throw new InvalidTokenException("Token inválido","Falha na autenticação do usuário.");
		}
	}

	@Override
	public User findByUserName(String username) {
		return userRepository.findByUserName(username);
	}

	private void updateDatasUserLogin(User user) {
		String token = generateToken();
		user.setToken(token);
		user.setLastLogin(LocalDateTime.now());
		userRepository.save(user);
	}

	private String generateToken() {
		String uuid = UUID.randomUUID().toString().replace("-", "");
		String date = DateUtil.formattNanoSecond(LocalDateTime.now());

		return uuid.concat(date);
	}
}
