package br.com.facility.service;

import br.com.facility.model.User;

public interface IUserService {

	public User findById(Long id);

	public User save(User user);

	public void delete(Long id);
}
