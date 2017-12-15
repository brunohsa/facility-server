package br.com.facility.model;

import br.com.facility.json.request.ExpenseRequest;
import br.com.facility.model.enuns.PaymentType;
import br.com.facility.model.enuns.StatusFinance;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "EXPENSE")
public class Expense extends Finance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "EXPIRATION_DATE")
    private LocalDate expirationDate;

    @Column(name = "PAYMENT_DATE")
    private LocalDate paymentDate;

    public Expense() {
    }

    public Expense(ExpenseRequest expenseJson, User user) {
        super(expenseJson.getValue(), user, expenseJson.getDescription(), expenseJson.getObservation(), expenseJson.getPaymentType(), expenseJson.getStatus());
        this.id = expenseJson.getId();
        this.expirationDate = expenseJson.getExpirationDate();
        this.paymentDate = expenseJson.getPaymentDate();
    }

    public Expense(BigDecimal value, User user, String description, String observation, PaymentType paymentType, StatusFinance status, LocalDate expirationDate,
                   LocalDate paymentDate) {
        super(value, user, description, observation, paymentType, status);
        this.expirationDate = expirationDate;
        this.paymentDate = paymentDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }
}
