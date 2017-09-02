package br.com.facility.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.facility.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
