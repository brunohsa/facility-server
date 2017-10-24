package br.com.facility.repository;

import br.com.facility.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	@Query("select u from USER u where u.userName = :userName and u.password = :password")
	User findUserByUserNameAndPassword(@Param("userName") String userName,@Param("password") String password);
}
