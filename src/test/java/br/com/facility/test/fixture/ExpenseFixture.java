package br.com.facility.test.fixture;

import br.com.facility.model.Expense;
import br.com.facility.model.enuns.PaymentType;
import br.com.facility.model.enuns.StatusFinance;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExpenseFixture implements IFixture<Expense> {

	public static final Expense CELL_PHONE_BRUNO = new Expense(new BigDecimal(2500.00), UserFixture.BRUNO_ARAUJO, "Cell Phone Test", "", PaymentType.CREDIT, StatusFinance.PAID, null, LocalDate.parse("2017-10-25"));

	@Override
	public List<Expense> getAll() {
		List<Expense> expensesFixture = new ArrayList<>();
		expensesFixture.add(CELL_PHONE_BRUNO);

		return expensesFixture;
	}
}