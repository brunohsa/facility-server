package br.com.facility.repository;

import br.com.facility.model.Expense;
import br.com.facility.model.enuns.StatusFinance;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ExpenseRepository extends CrudRepository<Expense, Long> {

	@Query("select e from EXPENSE e where e.status = :status")
	List<Expense> getExpensesByStatus(@Param("status") StatusFinance statusFinance);

	//filtra as despesas pela data envida até data atual.
	@Query("select e from EXPENSE e where e.releaseDate >= :date")
	List<Expense> filterExpensesByDate(@Param("date") LocalDateTime date);
}
