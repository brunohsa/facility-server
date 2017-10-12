package br.com.facility.webservice;

import br.com.facility.service.IExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("expense")
public class ExpenseWebService {

	@Autowired
	private IExpenseService expenseService;

	@RequestMapping(value = "/find", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public void findById(@RequestParam("/id") Long id){}

	@RequestMapping(value = "/insert", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public void insert(@RequestParam("/expense") String jsonExpense){}

	@RequestMapping(value = "/delte", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.DELETE)
	public void delete(@RequestParam("/id") Long id){}

	@RequestMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public void update(@RequestParam("/expense") String jsonExpense){}

}
