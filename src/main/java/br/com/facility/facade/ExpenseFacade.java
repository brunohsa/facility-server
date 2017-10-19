package br.com.facility.facade;

import br.com.facility.json.ExpenseJson;
import br.com.facility.model.Expense;
import br.com.facility.model.User;
import br.com.facility.service.ExpenseService;
import br.com.facility.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
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
	public ExpenseJson save(ExpenseJson expenseJson) {
		User user = userService.findById(expenseJson.getUserId());
		if (Objects.isNull(user)) {
			//throw a exception
		}
		Expense expense = new Expense(expenseJson, user);
		Expense expenseSaved = expenseService.save(expense);
		return new ExpenseJson(expenseSaved);
	}

	@Override
	public ExpenseJson findById(Long id) {
		Expense expense = expenseService.findById(id);
		//verificar se é nulo, se for lança exceção
		return new ExpenseJson(expense);
	}

	@Override
	public List<ExpenseJson> filterByDate(LocalDateTime dateTime) {
		dateTime.with(ChronoField.HOUR_OF_DAY, 0).with(ChronoField.MINUTE_OF_DAY, 0).with(ChronoField.SECOND_OF_MINUTE, 0);

		List<Expense> expenses = expenseService.filterExpensesByDate(dateTime);
		List<ExpenseJson> expensesJson = new ArrayList<>();
		expenses.forEach(expense -> expensesJson.add(new ExpenseJson(expense)));

		return expensesJson;
	}

	@Override
	public void delete(Long id) {
		userService.delete(id);
	}

}
