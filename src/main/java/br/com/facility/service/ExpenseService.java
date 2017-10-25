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
public class ExpenseService extends GenericService<Expense, ExpenseRepository> implements IExpenseService {

	@Autowired
	private ExpenseRepository repository;

	@Override
	public List<Expense> getExpensesByStatus(StatusFinance status) {
		return repository.getByStatus(status);
	}

	@Override
	public List<Expense> filterExpensesByDate(LocalDate date) {
		LocalDateTime dateTime = LocalDateTime.of(date, LocalTime.MIDNIGHT);
		return repository.filterExpensesByDate(dateTime);
	}

	@Override
	public void deleteExpensesByUserId(Long id) {
		repository.deleteByUserId(id);
	}
}
