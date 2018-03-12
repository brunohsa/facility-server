package br.com.facility.test.domain.model;

import br.com.facility.domain.model.ExpensePayment;
import br.com.facility.domain.model.Payment;
import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class ExpensePaymentTest {

    @Test
    public void giveValidParametersThenReturnANewPayment() {
        LocalDate expirationDate = LocalDate.parse("2017-10-25");

        Payment payment = EasyMock.createMock(Payment.class);
        ExpensePayment expensePayment = new ExpensePayment(payment, expirationDate);

        Assert.assertNotNull(expensePayment);
        Assert.assertEquals(expirationDate, expensePayment.getExpirationDate());
    }

    @Test
    public void giveNullExpirationDateThenReturnANewPayment() {
        LocalDate expirationDate = null;

        Payment payment = EasyMock.createMock(Payment.class);
        ExpensePayment expensePayment = new ExpensePayment(payment, expirationDate);

        Assert.assertNotNull(expensePayment);
        Assert.assertEquals(expirationDate, expensePayment.getExpirationDate());
    }

    @Test(expected = RuntimeException.class)
    public void giveNullPaymentThenThrowAnException() {
        Payment payment = null;
        new ExpensePayment(payment, LocalDate.parse("2017-10-25"));
    }
}
