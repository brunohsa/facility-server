package br.com.facility.repository;

import br.com.facility.model.Expense;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ExpenseRepository extends IncomeAndExposeRepositoryBase<Expense, Long> {

	//filtra as despesas pela data envida até data atual.
	@Query("select e from EXPENSE e join e.user user where e.releaseDate >= :date AND user.username = :username")
	List<Expense> filterExpensesByDate(@Param("date") LocalDateTime date, @Param("username") String token);
}
