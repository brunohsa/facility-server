package br.com.facility.service;

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
	public User doLogin(String userName, String password) {
		User user = userRepository.findUserByUserNameAndPassword(userName, password);
		if (Objects.isNull(user)) {
			//throw a exception
			return null;
		}
		updateUserLoginData(user);
		return user;
	}

	private void updateUserLoginData(User user) {
		String token = generateToken();
		user.setToken(token);
		user.setLastLogin(LocalDateTime.now());
		userRepository.save(user);
	}

	private String generateToken() {
		String separator = "|";

		String date = DateUtil.formattNanoSecond(LocalDateTime.now());
		String uuid = UUID.randomUUID().toString().replace("-", "");

		return date.concat(separator).concat(uuid);
	}
}
