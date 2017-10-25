package br.com.facility.webservice;

import br.com.facility.facade.IExpenseFacade;
import br.com.facility.json.request.ExpenseRequest;
import br.com.facility.json.response.ExpenseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseWebService {

    @Autowired
    private IExpenseFacade expenseFacade;

    //@RequestHeader("Accept-Encoding") String header <- MANEIRA DE RECUPERAR UM HEADER

    @RequestMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity findById(@PathVariable("id") Long id) {
        ExpenseResponse expense = expenseFacade.findById(id);
        return ResponseEntity.ok(expense);
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
        return ResponseEntity.ok(updatedExpense);
    }

    @RequestMapping(value = "/filterbydate/{date}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity filterByDate(@PathVariable("date") LocalDate localDate) {
        List<ExpenseResponse> expensesJson = expenseFacade.filterByDate(localDate);
        return ResponseEntity.ok(expensesJson);
    }
}
