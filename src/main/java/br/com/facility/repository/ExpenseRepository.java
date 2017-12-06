package br.com.facility.repository;

import br.com.facility.model.Expense;
import br.com.facility.model.enuns.PaymentType;
import br.com.facility.model.enuns.StatusFinance;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ExpenseRepository extends IncomeAndExposeRepositoryBase<Expense, Long> {

	//filtra as despesas pela data envida até data atual.
	@Query("select e from EXPENSE e join e.user user where e.releaseDate >= :date AND user.username = :username")
	List<Expense> filterExpensesByDate(@Param("date") LocalDateTime date, @Param("username") String token);

	@Transactional
	@Modifying
	@Query("update EXPENSE set value =:value, description =:description, observation =:observation, "
			+ "paymentType =:paymentType, status =:status, expirationDate =:expirationDate "
			+ "WHERE id = :id")
	Integer update(@Param("value") BigDecimal value, @Param("description") String description, @Param("observation") String observation,
			@Param("paymentType") PaymentType paymentType, @Param("status") StatusFinance status, @Param("expirationDate") LocalDate expirationDate,
			@Param("id") Long id);
}
