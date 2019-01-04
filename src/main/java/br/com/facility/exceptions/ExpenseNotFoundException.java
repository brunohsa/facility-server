package br.com.facility.exceptions;

public class ExpenseNotFoundException extends NotFoundException {

    public ExpenseNotFoundException() {
        super(ErrorMessages.EXPENSE_NOT_FOUND);
    }
}
