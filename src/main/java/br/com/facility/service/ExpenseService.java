package br.com.facility.service;

import br.com.facility.model.Expense;
import br.com.facility.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ExpenseService extends GenericService<Expense, ExpenseRepository> implements IExpenseService {


	public void getExpenseByDate(LocalDate date){

	}

	public void getPendingExpenses(){

	}
}
