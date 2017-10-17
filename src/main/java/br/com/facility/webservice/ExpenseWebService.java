package br.com.facility.webservice;

import br.com.facility.model.Expense;
import br.com.facility.service.IExpenseService;
import br.com.facility.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/expenses")
public class ExpenseWebService {

	@Autowired
	private IExpenseService expenseService;

	@RequestMapping(value = "/findbyid/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity findById(@PathVariable("id") Long id) {
		Expense expense = expenseService.findById(id);
		return new ResponseEntity(expense, HttpStatus.OK);
	}

	@RequestMapping(value = "/insert", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity insert(@RequestParam("expense") String jsonExpense){
		Expense expense = null;
		try {
			expense = JsonUtil.convertJsonToObject(jsonExpense, Expense.class);
			expense.setReleaseDate(LocalDateTime.now());
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.OK);
		}
		Expense newExpense = expenseService.save(expense);
		return new ResponseEntity(newExpense, HttpStatus.OK);
	}

	@RequestMapping(value = "/delete/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Long id){
		expenseService.delete(id);
	}

	@RequestMapping(value = "/update/{expense}", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity update(@PathVariable("expense") String jsonExpense){
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
