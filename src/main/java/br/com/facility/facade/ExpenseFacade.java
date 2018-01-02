package br.com.facility.facade;

import br.com.facility.exceptions.webservice.InvalidEnumTypeException;
import br.com.facility.json.request.ExpenseRequest;
import br.com.facility.json.response.ExpenseResponse;
import br.com.facility.model.Expense;
import br.com.facility.model.User;
import br.com.facility.model.enuns.PaymentType;
import br.com.facility.model.enuns.FinanceStatus;
import br.com.facility.service.ExpenseService;
import br.com.facility.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseFacade implements IExpenseFacade {

	@Autowired
	private UserService userService;

	@Autowired
	private ExpenseService expenseService;

	@Override
	public ExpenseResponse save(ExpenseRequest request) {
		User user = userService.findLoggedUser();
		PaymentType paymentType = getPaymentType(request.getPaymentType());
		FinanceStatus statusFinance = getStatusFinance(request.getStatus());

		Expense expense = new Expense(request.getValue(), user, request.getDescription(), request.getObservation(), paymentType, statusFinance,
				request.getExpirationDate(), request.getPaymentDate());
		expense = expenseService.save(expense);

		return new ExpenseResponse(expense);
	}

	@Override
	public ExpenseResponse update(ExpenseRequest expense) {
		PaymentType paymentType = getPaymentType(expense.getPaymentType());
		FinanceStatus statusFinance = getStatusFinance(expense.getStatus());

		Expense updatedExpense = expenseService
				.update(expense.getValue(), expense.getDescription(), expense.getObservation(), paymentType, statusFinance, expense.getExpirationDate(),
						expense.getId());

		return new ExpenseResponse(updatedExpense);
	}

	private FinanceStatus getStatusFinance(String status) {
		try {
			return FinanceStatus.valueOf(status);
		} catch (IllegalArgumentException e) {
			throw new InvalidEnumTypeException("StatusFinance");
		}
	}

	private PaymentType getPaymentType(String paymentType) {
		try {
			return PaymentType.valueOf(paymentType);
		} catch (IllegalArgumentException e) {
			throw new InvalidEnumTypeException("PaymentType");
		}
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
				.map(expense -> new ExpenseResponse(expense))
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
