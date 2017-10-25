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
		updateDatasUserLogin(user);
		return user;
	}

	private void updateDatasUserLogin(User user) {
		String token = generateToken(user.getUserName());
		user.setToken(token);
		user.setLastLogin(LocalDateTime.now());
		userRepository.save(user);
	}

	private String generateToken(String userName) {
		String separator = "|";

		String uuid = UUID.randomUUID().toString().replace("-", "");
		String date = DateUtil.formattNanoSecond(LocalDateTime.now());

		return userName.concat(separator).concat(uuid).concat(separator).concat(date);
	}
}
