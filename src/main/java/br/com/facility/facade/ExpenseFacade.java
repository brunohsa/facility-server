package br.com.facility.facade;

import br.com.facility.model.Expense;
import br.com.facility.model.User;
import br.com.facility.service.ExpenseService;
import br.com.facility.service.UserService;
import br.com.facility.webservice.model.request.ExpenseRequest;
import br.com.facility.webservice.model.response.ExpenseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseFacade implements IExpenseFacade {

    @Autowired
    private UserService userService;

    @Autowired
    private ExpenseService expenseService;

    @Override
    public ExpenseResponse save(ExpenseRequest expenseRequest) {
        User user = userService.findLoggedUser();
        Expense expense = new Expense(
                expenseRequest.getValue(),
                user,
                expenseRequest.getDescription(),
                expenseRequest.getObservation(),
                expenseRequest.getPaymentType(),
                expenseRequest.getStatus(),
                expenseRequest.getExpirationDate(),
                expenseRequest.getPaymentDate()
        );
        Expense expenseSaved = expenseService.save(expense);
        return mapExpenseToExpenseResponse(expenseSaved);
    }

    @Override
    public ExpenseResponse update(ExpenseRequest expense) {
//        Expense expenseSaved = expenseService.update(expense.getValue(), expense.getDescription(), expense.getObservation(), expense.getPaymentType(), expense.getStatus(),
//                expense.getExpirationDate(), expense.getId());
        return null;
    }

    @Override
    public ExpenseResponse findById(Long id) {
        Expense expense = expenseService.findById(id);
        return mapExpenseToExpenseResponse(expense);
    }

    @Override
    public List<ExpenseResponse> filterByDate(LocalDate date) {
        List<Expense> expenses = expenseService.filterExpensesByDate(date);
        List<ExpenseResponse> expensesJson = expenses.stream()
                .map(expense -> mapExpenseToExpenseResponse(expense))
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
                .map(expense -> mapExpenseToExpenseResponse(expense))
                .collect(Collectors.toList());

        return expensesJson;
    }

    private ExpenseResponse mapExpenseToExpenseResponse(Expense expense) {
        return new ExpenseResponse(
                expense.getValue(),
                expense.getDescription(),
                expense.getObservation(),
                expense.getPaymentType(),
                expense.getSituation(),
                expense.getId(),
                expense.getReleaseDate(),
                expense.getSituation().getAvaliableSituations()
        );
    }
}
