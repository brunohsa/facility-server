package br.com.facility.facade;

import br.com.facility.json.request.ExpenseRequest;
import br.com.facility.json.response.ExpenseResponse;

import java.time.LocalDate;
import java.util.List;

public interface IExpenseFacade {

	ExpenseResponse save(ExpenseRequest expenseRequest);

	ExpenseResponse findById(Long id);

	List<ExpenseResponse> filterByDate(LocalDate date, String token);

	void delete(Long id);

}
