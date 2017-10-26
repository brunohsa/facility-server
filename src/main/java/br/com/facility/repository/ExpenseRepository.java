package br.com.facility.repository;

import br.com.facility.model.Expense;
import br.com.facility.model.enuns.StatusFinance;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ExpenseRepository extends CrudRepository<Expense, Long> {

	List<Expense> getByStatus(@Param("status") StatusFinance statusFinance);

	//filtra as despesas pela data envida até data atual.
	@Query("select e from EXPENSE e where e.releaseDate >= :date AND e.user.token = :token")
	List<Expense> filterExpensesByDate(@Param("date") LocalDateTime date, @Param("token") String token);

	void deleteByUserId(@Param("id") Long userId);
}
