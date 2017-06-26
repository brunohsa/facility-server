package br.com.facility.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.facility.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
