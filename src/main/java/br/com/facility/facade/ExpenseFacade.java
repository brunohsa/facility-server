package br.com.facility.facade;

import br.com.facility.model.Expense;
import br.com.facility.model.User;
import br.com.facility.service.ExpenseService;
import br.com.facility.service.UserService;
import br.com.facility.webservice.model.request.ExpenseRequest;
import br.com.facility.webservice.model.response.ExpenseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ExpenseFacade implements IExpenseFacade {

	@Autowired
	private UserService userService;

	@Autowired
	private ExpenseService expenseService;

	@Override
	public ExpenseResponse save(ExpenseRequest expenseRequest) {
		User user = userService.findLoggedUser();
		Expense expense = new Expense(expenseRequest, user);
		Expense expenseSaved = expenseService.save(expense);
		return new ExpenseResponse(expenseSaved);
	}

	@Override
	public ExpenseResponse update(ExpenseRequest expense) {
		Expense expenseSaved = expenseService
				.update(expense.getValue(), expense.getDescription(), expense.getObservation(), expense.getPaymentType(), expense.getStatus(),
						expense.getExpirationDate(), expense.getId());
		return new ExpenseResponse(expenseSaved);
	}

	@Override
	public ExpenseResponse findById(Long id) {
		Expense expense = expenseService.findById(id);
		return new ExpenseResponse(expense);
	}

	@Override
	public List<ExpenseResponse> filterByDate(LocalDate date) {
		List<Expense> expenses = expenseService.filterExpensesByDate(date);
		List<ExpenseResponse> expensesJson = expenses.stream()
				.map(ExpenseResponse::new)
				.collect(Collectors.toList());

		return expensesJson;
	}

	@Override
	public void delete(Long id) {
		expenseService.delete(id);
	}

	@Override
	public List<ExpenseResponse> findAll() {
		List<Expense> expenses = expenseService.findAll();
		List<ExpenseResponse> expensesJson = expenses.stream()
				.map(expense -> new ExpenseResponse(expense))
				.collect(Collectors.toList());

		return expensesJson;
	}

}
