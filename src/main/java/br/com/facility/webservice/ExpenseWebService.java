package br.com.facility.webservice;

import br.com.facility.facade.IExpenseFacade;
import br.com.facility.json.JsonError;
import br.com.facility.json.request.ExpenseRequest;
import br.com.facility.json.response.ExpenseResponse;
import br.com.facility.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseWebService {

    @Autowired
    private IExpenseFacade expenseFacade;

    //@RequestHeader("Accept-Encoding") String header <- MANEIRA DE RECUPERAR UM HEADER

    @RequestMapping(value = "/findbyid/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity findById(@PathVariable("id") Long id) {
        ExpenseResponse expense = expenseFacade.findById(id);
        return new ResponseEntity(expense, HttpStatus.OK);
    }

    @RequestMapping(value = "/insert", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<ExpenseResponse> insert(@RequestBody ExpenseRequest expense) {
        ExpenseResponse newExpense = expenseFacade.save(expense);
        return new ResponseEntity(newExpense, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/delete/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        expenseFacade.delete(id);
    }

    @RequestMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity update(@RequestBody ExpenseRequest jsonRequest) {
        ExpenseResponse updatedExpense = expenseFacade.save(jsonRequest);
        return new ResponseEntity(updatedExpense, HttpStatus.OK);
    }

    @RequestMapping(value = "/filterbydate/{date}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity filterByDate(@PathVariable("date") LocalDateTime localDateTime) {
        List<ExpenseResponse> expensesJson = expenseFacade.filterByDate(localDateTime);
        return new ResponseEntity(expensesJson, HttpStatus.OK);
    }

    private JsonError createMessageError(HttpStatus status, String cause, String description) {
        return new JsonError(status, Messages.getMessage(cause), Messages.getMessage(description));
    }
}
