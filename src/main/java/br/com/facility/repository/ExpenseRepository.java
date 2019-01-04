package br.com.facility.repository;

import br.com.facility.model.Expense;
import br.com.facility.model.enuns.FinanceStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends FinanceRepository {

	//filtra as despesas pela data envida até data atual.
	@Query("SELECT e FROM EXPENSE e JOIN e.user user WHERE e.releaseDate >= :from AND e.releaseDate <= :to AND user.username = :username")
	List<Expense> filterExpensesByDate(@Param("from") LocalDateTime from, @Param("to") LocalDateTime to, @Param("username") String token);

	List<Expense> getByStatusAndUserUsername(@Param("status") FinanceStatus status, @Param("username") String username);

	Optional<Expense> findByIdAndUserUsername(@Param("id") Long id, @Param("username") String username);

	List<Expense> findAllByUserUsername(@Param("username") String username);
}
