package br.com.facility.webservice;

import br.com.facility.model.Expense;
import br.com.facility.service.IExpenseService;
import br.com.facility.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/expenses")
public class ExpenseWebService {

	@Autowired
	private IExpenseService expenseService;

	@RequestMapping(value = "/find", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity findById(@RequestParam("/id") Long id) {
		Expense expense = expenseService.findById(id);
		return new ResponseEntity(expense, HttpStatus.OK);
	}

	@RequestMapping(value = "/insert", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity insert(@RequestParam("/expense") String jsonExpense){
		Expense expense = null;
		try {
			expense = JsonUtil.convertJsonToObject(jsonExpense, Expense.class);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.OK);
		}
		Expense newExpense = expenseService.save(expense);
		return new ResponseEntity(newExpense, HttpStatus.OK);
	}

	@RequestMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.DELETE)
	public void delete(@RequestParam("/id") Long id){
		expenseService.delete(id);
	}

	@RequestMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity update(@RequestParam("/expense") String jsonExpense){
		Expense expense = null;
		try {
			expense = JsonUtil.convertJsonToObject(jsonExpense, Expense.class);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.OK);
		}
		Expense newExpense = expenseService.save(expense);
		return new ResponseEntity(newExpense, HttpStatus.OK);
	}

}
