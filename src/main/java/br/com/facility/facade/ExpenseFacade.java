package br.com.facility.facade;

import br.com.facility.json.request.ExpenseRequest;
import br.com.facility.json.response.ExpenseResponse;
import br.com.facility.model.Expense;
import br.com.facility.model.User;
import br.com.facility.service.ExpenseService;
import br.com.facility.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ExpenseFacade implements IExpenseFacade {

	@Autowired
	private UserService userService;

	@Autowired
	private ExpenseService expenseService;

	@Override
	public ExpenseResponse save(ExpenseRequest expenseRequest) {
		User user = userService.findById(expenseRequest.getUserId());
		if (Objects.isNull(user)) {
			//throw a exception
		}
		Expense expense = new Expense(expenseRequest, user);
		Expense expenseSaved = expenseService.save(expense);
		return new ExpenseResponse(expenseSaved);
	}

	@Override
	public ExpenseResponse findById(Long id) {
		Expense expense = expenseService.findById(id);
		//verificar se é nulo, se for lança exceção
		return new ExpenseResponse(expense);
	}

	@Override
	public List<ExpenseResponse> filterByDate(LocalDate date) {
		LocalDateTime dateTime = LocalDateTime.of(date, LocalTime.MIDNIGHT);

		List<Expense> expenses = expenseService.filterExpensesByDate(dateTime);
		List<ExpenseResponse> expensesJson = new ArrayList<>();
		expenses.forEach(expense -> expensesJson.add(new ExpenseResponse(expense)));

		return expensesJson;
	}

	@Override
	public void delete(Long id) {
		expenseService.delete(id);
	}

}
