package br.com.facility.service;

import br.com.facility.model.User;
import br.com.facility.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends GenericService<User> implements IUserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	protected CrudRepository getRepository() {
		return userRepository;
	}
}
