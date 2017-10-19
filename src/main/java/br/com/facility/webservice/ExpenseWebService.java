package br.com.facility.webservice;

import br.com.facility.json.ExpenseJson;
import br.com.facility.json.JsonError;
import br.com.facility.model.Expense;
import br.com.facility.model.User;
import br.com.facility.service.IExpenseService;
import br.com.facility.service.IUserService;
import br.com.facility.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

@RestController
@RequestMapping("/expenses")
public class ExpenseWebService {

    @Autowired
    private IExpenseService expenseService;

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/findbyid/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity findById(@PathVariable("id") Long id) {
        Expense expense = expenseService.findById(id);
        if (Objects.isNull(expense)) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        ExpenseJson jsonExpense = new ExpenseJson(expense);
        return new ResponseEntity(jsonExpense, HttpStatus.OK);
    }

    @RequestMapping(value = "/insert", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity insert(@RequestParam("expense") String jsonExpense) {
        ExpenseJson expenseJson = null;
        try {
            expenseJson = JsonUtil.convertJsonToObject(jsonExpense, ExpenseJson.class);
        } catch (IOException e) {
            JsonError jsonError = new JsonError(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause().getMessage(), e.getMessage());
            return new ResponseEntity(jsonError, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        User user = userService.findById(expenseJson.getUserId());
        if (Objects.isNull(user)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        Expense expense = new Expense(expenseJson, user);
        Expense newExpense = expenseService.save(expense);
        return new ResponseEntity(new ExpenseJson(newExpense), HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        expenseService.delete(id);
    }

    @RequestMapping(value = "/update/{expense}", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity update(@PathVariable("expense") String jsonExpense) {
        Expense expense = null;
        try {
            expense = JsonUtil.convertJsonToObject(jsonExpense, Expense.class);
        } catch (IOException e) {
            return new ResponseEntity(HttpStatus.OK);
        }
        Expense newExpense = expenseService.save(expense);
        return new ResponseEntity(newExpense, HttpStatus.OK);
    }

}
