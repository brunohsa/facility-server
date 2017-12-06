package br.com.facility.service;

import br.com.facility.model.Expense;
import br.com.facility.model.enuns.StatusFinance;
import br.com.facility.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class ExpenseService extends IncomeAndExpenseServiceBase<Expense, ExpenseRepository> {

	@Autowired
	private ExpenseRepository repository;

	public List<Expense> getExpensesByStatus(StatusFinance status) {
		return repository.getByStatusAndUserUsername(status, getLoggedUser());
	}

	public List<Expense> filterExpensesByDate(LocalDate date) {
		LocalDateTime dateTime = LocalDateTime.of(date, LocalTime.MIDNIGHT);
		return repository.filterExpensesByDate(dateTime, getLoggedUser());
	}
}
