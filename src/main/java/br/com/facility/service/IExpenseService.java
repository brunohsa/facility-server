package br.com.facility.service;

import br.com.facility.model.Expense;

public interface IExpenseService {

	public Expense findById(Long id);

	public Expense save(Expense expense);

	public void delete(Long id);
}
