package br.com.facility.service;

import br.com.facility.model.Expense;
import br.com.facility.model.enuns.PaymentType;
import br.com.facility.model.enuns.FinanceStatus;
import br.com.facility.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class ExpenseService extends IncomeAndExpenseServiceBase<Expense, ExpenseRepository> {

	@Autowired
	private ExpenseRepository repository;

	public List<Expense> getExpensesByStatus(FinanceStatus status) {
		return repository.getByStatusAndUserUsername(status, getLoggedUser());
	}

	public List<Expense> filterExpensesByDate(LocalDate date) {
		LocalDateTime dateTime = LocalDateTime.of(date, LocalTime.MIDNIGHT);
		return repository.filterExpensesByDate(dateTime, getLoggedUser());
	}

	public Expense update(BigDecimal value, String description, String observation, PaymentType paymentType, FinanceStatus status, LocalDate expirationDate,
			Long id) {

		Integer update = repository.update(value, description, observation, paymentType, status, expirationDate, id);
		if (update == 1) {
			return repository.findOne(id);
		}
		//THROW EXCEPTION
		return null;
	}
}
