package br.com.facility.service;

import br.com.facility.model.Expense;
import br.com.facility.model.enuns.StatusFinance;

import java.time.LocalDate;

import java.util.List;

public interface IExpenseService {

	public Expense findById(Long id);

	public Expense save(Expense expense);

	public void delete(Long id);

	public List<Expense> getExpensesByStatus(StatusFinance status);

	public List<Expense> filterExpensesByDate(LocalDate date);
}
