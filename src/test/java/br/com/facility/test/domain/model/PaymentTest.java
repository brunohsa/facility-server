package br.com.facility.test.domain.model;

import br.com.facility.domain.model.Payment;
import br.com.facility.domain.model.Value;
import br.com.facility.model.enuns.PaymentType;
import br.com.facility.model.enuns.StatusFinance;
import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PaymentTest {


    @Test
    public void gitveAllRequiredFieldsThenReturnNewPayment() {
        LocalDateTime releaseDate = LocalDateTime.now();
        PaymentType paymentType = PaymentType.CREDIT;
        StatusFinance statusFinance = StatusFinance.PAID;
        Value value = EasyMock.createMock(Value.class);
        LocalDate paymentDate = LocalDate.parse("2018-01-10");

        Payment payment = new Payment(releaseDate, paymentType, statusFinance, value, paymentDate);

        Assert.assertNotNull(payment);
        Assert.assertEquals(releaseDate, payment.getReleaseDate());
        Assert.assertEquals(paymentType, payment.getPaymentType());
        Assert.assertEquals(statusFinance, payment.getStatus());
        Assert.assertEquals(value, payment.getValue());
        Assert.assertEquals(paymentDate, payment.getPaymentDate());
    }

    @Test
    public void giveStatusFinanceDifferentThanPaidAndPaymentDateNullThenReturnNewPayment() {
        LocalDateTime releaseDate = LocalDateTime.now();
        PaymentType paymentType = PaymentType.CREDIT;
        StatusFinance statusFinance = StatusFinance.OVERDUE;
        Value value = EasyMock.createMock(Value.class);
        LocalDate paymentDate = null;

        Payment payment = new Payment(releaseDate, paymentType, statusFinance, value, paymentDate);

        Assert.assertNotNull(payment);
        Assert.assertEquals(releaseDate, payment.getReleaseDate());
        Assert.assertEquals(paymentType, payment.getPaymentType());
        Assert.assertEquals(statusFinance, payment.getStatus());
        Assert.assertEquals(value, payment.getValue());
        Assert.assertEquals(paymentDate, payment.getPaymentDate());
    }

    @Test(expected = RuntimeException.class)
    public void giveReleaseDateNullThenThrowAnException() {
        LocalDateTime releaseDate = null;
        PaymentType paymentType = PaymentType.CREDIT;
        StatusFinance statusFinance = StatusFinance.PAID;
        Value value = EasyMock.createMock(Value.class);
        LocalDate paymentDate = LocalDate.parse("2018-01-10");

        new Payment(releaseDate, paymentType, statusFinance, value, paymentDate);
    }

    @Test(expected = RuntimeException.class)
    public void givePaymentTypeNullThenThrowAnException() {
        LocalDateTime releaseDate = LocalDateTime.now();
        PaymentType paymentType = null;
        StatusFinance statusFinance = StatusFinance.PAID;
        Value value = EasyMock.createMock(Value.class);
        LocalDate paymentDate = LocalDate.parse("2018-01-10");

        new Payment(releaseDate, paymentType, statusFinance, value, paymentDate);
    }

    @Test(expected = RuntimeException.class)
    public void giveStatusFinanceNullThenThrowAnException() {
        LocalDateTime releaseDate = LocalDateTime.now();
        PaymentType paymentType = PaymentType.CREDIT;
        StatusFinance statusFinance = null;
        Value value = EasyMock.createMock(Value.class);
        LocalDate paymentDate = null;

        new Payment(releaseDate, paymentType, statusFinance, value, paymentDate);
    }

    @Test(expected = RuntimeException.class)
    public void giveValueNullThenThrowAnException() {
        LocalDateTime releaseDate = LocalDateTime.now();
        PaymentType paymentType = PaymentType.CREDIT;
        StatusFinance statusFinance = StatusFinance.PAID;
        Value value = null;
        LocalDate paymentDate = LocalDate.parse("2018-01-10");

        new Payment(releaseDate, paymentType, statusFinance, value, paymentDate);
    }

    @Test(expected = RuntimeException.class)
    public void giveStatusFinancePaidWithoutPaymentDateThenThrowAnException() {
        LocalDateTime releaseDate = null;
        PaymentType paymentType = PaymentType.CREDIT;
        StatusFinance statusFinance = StatusFinance.PAID;
        Value value = EasyMock.createMock(Value.class);
        LocalDate paymentDate = LocalDate.parse("2018-01-10");

        new Payment(releaseDate, paymentType, statusFinance, value, paymentDate);
    }

    @Test(expected = RuntimeException.class)
    public void giveStatusFinanceDifferentOfPaidAndWithPaymentDateThenThrowAnException() {
        LocalDateTime releaseDate = LocalDateTime.now();
        PaymentType paymentType = PaymentType.CREDIT;
        StatusFinance statusFinance = StatusFinance.CANCELLED;
        Value value = EasyMock.createMock(Value.class);
        LocalDate paymentDate = LocalDate.parse("2018-01-10");

        new Payment(releaseDate, paymentType, statusFinance, value, paymentDate);
    }
}
