package br.com.facility.webservice;

import br.com.facility.facade.IExpenseFacade;
import br.com.facility.json.request.ExpenseRequest;
import br.com.facility.json.response.ExpenseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseWebService {

    @Autowired
    private IExpenseFacade expenseFacade;

    @RequestMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity findById(@PathVariable("id") Long id) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String user = auth.getName();

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

    @RequestMapping(value = "/filter/{date}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity filterByDate(@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("date") LocalDate localDate,
            @RequestHeader("token") String token) {
        List<ExpenseResponse> expensesJson = expenseFacade.filterByDate(localDate, token);
        return ResponseEntity.ok(expensesJson);
    }
}
