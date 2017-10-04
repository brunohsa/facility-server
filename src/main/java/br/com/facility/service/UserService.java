package br.com.facility.service;

import br.com.facility.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends GenericService {

	@Autowired
	private UserRepository userRepository;

	public UserService(CrudRepository repository) {
		super(repository);
	}
}
