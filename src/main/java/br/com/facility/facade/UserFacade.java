package br.com.facility.facade;

import br.com.facility.service.ExpenseService;
import br.com.facility.service.IncomeService;
import br.com.facility.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserFacade implements IUserFacade {

    @Autowired
    private UserService userService;

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private IncomeService incomeService;

    @Override
    public void delete() {
        incomeService.deleteAll();
        expenseService.deleteAll();
        userService.delete();
    }
}
