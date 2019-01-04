package br.com.facility.webservice;

import br.com.facility.facade.IExpenseFacade;
import br.com.facility.util.DateUtil;
import br.com.facility.webservice.model.request.ExpenseRequest;
import br.com.facility.webservice.model.response.ExpenseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseWebService {

    @Autowired
    private IExpenseFacade expenseFacade;

    @RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity findById(@PathVariable("id") Long id) {
        ExpenseResponse expense = expenseFacade.findById(id);
        return ResponseEntity.ok(expense);
    }

    @RequestMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity findAll() {
        List<ExpenseResponse> expenses = expenseFacade.findAll();
        return ResponseEntity.ok(expenses);
    }

    @RequestMapping(value = "/insert", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<ExpenseResponse> insert(@RequestBody ExpenseRequest expense) {
        ExpenseResponse newExpense = expenseFacade.save(expense);
        return new ResponseEntity(newExpense, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}/delete", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        expenseFacade.delete(id);
    }

    @RequestMapping(value = "{id}/update", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody ExpenseRequest jsonRequest) {
        ExpenseResponse updatedExpense = expenseFacade.update(id, jsonRequest);
        return ResponseEntity.ok(updatedExpense);
    }

    @RequestMapping(value = "/from/{from}/to/{to}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity filterByDate(@PathVariable("from") String from, @PathVariable("to") String to) {
        LocalDate fromDate = DateUtil.getLocalDate(from);
        LocalDate toDate = DateUtil.getLocalDate(to);

        List<ExpenseResponse> expensesJson = expenseFacade.filterByDate(fromDate, toDate);
        return ResponseEntity.ok(expensesJson);
    }

    @RequestMapping(value = "{id}/pay", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity pay(@PathVariable("id") Long id) {
        return ResponseEntity.ok(expenseFacade.pay(id));
    }

    @RequestMapping(value = "{id}/cancell", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity cancell(@PathVariable("id") Long id) {
        return ResponseEntity.ok(expenseFacade.cancell(id));
    }

    @RequestMapping(value = "{id}/overdue", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity overdue(@PathVariable("id") Long id) {
        return ResponseEntity.ok(expenseFacade.overdue(id));
    }
}
