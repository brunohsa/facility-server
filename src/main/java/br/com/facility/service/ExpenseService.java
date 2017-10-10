package br.com.facility.service;

import br.com.facility.repository.ExpenseRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService extends GenericService<ExpenseRepository> implements IExpenseService {

    private ExpenseRepository expenseRepository;

    @Override
    protected CrudRepository getRepository() {
        return expenseRepository;
    }
}
