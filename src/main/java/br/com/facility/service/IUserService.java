package br.com.facility.service;

import br.com.facility.exceptions.InvalidLoginException;
import br.com.facility.exceptions.InvalidTokenException;
import br.com.facility.model.User;

public interface IUserService {

	User findById(Long id);

	User save(User user);

	void delete(Long id);

	User doLogin(String userName, String password) throws InvalidLoginException;

	void validateUser(String token) throws InvalidTokenException;
}
