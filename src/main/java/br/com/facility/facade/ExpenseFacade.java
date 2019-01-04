package br.com.facility.facade;

import br.com.facility.exceptions.ExpenseNotFoundException;
import br.com.facility.model.Expense;
import br.com.facility.model.User;
import br.com.facility.service.ExpenseService;
import br.com.facility.service.StatusMachineService;
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

    @Autowired
    private StatusMachineService statusMachineService;

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

        return mapExpenseToExpenseResponse(expenseService.save(expense));
    }

    @Override
    public ExpenseResponse update(Long id, ExpenseRequest expenseRequest) {
        Expense expense = expenseService.findById(id);

        if (expense == null) {
            throw new ExpenseNotFoundException();
        }

        expense.setDescription(expenseRequest.getDescription());
        expense.setObservation(expenseRequest.getObservation());
        expense.setExpirationDate(expenseRequest.getExpirationDate());
        expense.setValue(expenseRequest.getValue());

        return mapExpenseToExpenseResponse(expenseService.save(expense));
    }

    @Override
    public ExpenseResponse findById(Long id) {
        Expense expense = expenseService.findById(id);
        return mapExpenseToExpenseResponse(expense);
    }

    @Override
    public List<ExpenseResponse> filterByDate(LocalDate from, LocalDate to) {
        List<Expense> expenses = expenseService.filterExpensesByDate(from, to);
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

    @Override
    public ExpenseResponse pay(Long id) {
        Expense expense = expenseService.findById(id);
        return mapExpenseToExpenseResponse((Expense) statusMachineService.toPaid(expense));
    }

    @Override
    public ExpenseResponse cancell(Long id) {
        Expense expense = expenseService.findById(id);
        return mapExpenseToExpenseResponse((Expense) statusMachineService.toCancelled(expense));
    }

    @Override
    public ExpenseResponse overdue(Long id) {
        Expense expense = expenseService.findById(id);
        return mapExpenseToExpenseResponse((Expense) statusMachineService.toOverdue(expense));
    }

    private ExpenseResponse mapExpenseToExpenseResponse(Expense expense) {
        return new ExpenseResponse(
                expense.getValue(),
                expense.getDescription(),
                expense.getObservation(),
                expense.getPaymentType(),
                expense.getStatus(),
                expense.getId(),
                expense.getReleaseDate(),
                expense.getStatus().getAvaliableSituations()
        );
    }
}
