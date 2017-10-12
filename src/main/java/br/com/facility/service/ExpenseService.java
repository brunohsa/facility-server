package br.com.facility.service;

import br.com.facility.model.Expense;
import br.com.facility.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService extends GenericService<Expense, ExpenseRepository> {
}
