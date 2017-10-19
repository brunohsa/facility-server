package br.com.facility.facade;

import br.com.facility.json.ExpenseJson;

import java.time.LocalDateTime;
import java.util.List;

public interface IExpenseFacade {

	ExpenseJson save(ExpenseJson expenseJson);

	ExpenseJson findById(Long id);

	List<ExpenseJson> filterByDate(LocalDateTime dateTime);

	void delete(Long id);

}
