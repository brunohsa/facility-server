package br.com.facility.facade;

import br.com.facility.webservice.model.request.ExpenseRequest;
import br.com.facility.webservice.model.response.ExpenseResponse;

import java.time.LocalDate;
import java.util.List;

public interface IExpenseFacade {

	ExpenseResponse save(ExpenseRequest expenseRequest);

	ExpenseResponse update(Long id, ExpenseRequest expenseRequest);

	ExpenseResponse findById(Long id);

	List<ExpenseResponse> filterByDate(LocalDate date);

	void delete(Long id);

	List<ExpenseResponse> findAll();

}
