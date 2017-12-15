package br.com.facility.repository;

import br.com.facility.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	Optional<User> findByUsername(@Param("username") String username);

	void deleteByUsername(@Param("username") String username);
}
