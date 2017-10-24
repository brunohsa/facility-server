package br.com.facility.service;

import br.com.facility.model.User;

public interface IUserService {

	User findById(Long id);

	User save(User user);

	void delete(Long id);

	User doLogin(String userName, String password);
}
