package br.com.facility.repository;

import br.com.facility.model.Expense;
import br.com.facility.model.enuns.FinanceSituation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends CrudRepository<Expense, Long> {

	//filtra as despesas pela data envida até data atual.
	@Query("select e from EXPENSE e join e.user user where e.releaseDate >= :date AND user.username = :username")
	List<Expense> filterExpensesByDate(@Param("date") LocalDateTime date, @Param("username") String token);

	List<Expense> getBySituationAndUserUsername(@Param("situation") FinanceSituation situation, @Param("username") String username);

	Optional<Expense> findByIdAndUserUsername(@Param("id") Long id, @Param("username") String username);

	List<Expense> findAllByUserUsername(@Param("username") String username);
}
