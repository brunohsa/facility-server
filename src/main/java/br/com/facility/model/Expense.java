package br.com.facility.model;

import br.com.facility.model.enuns.PaymentType;
import br.com.facility.model.enuns.StatusFinance;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "EXPENSE")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Expense extends Finance{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "EXPIRATION_DATE")
    private LocalDate expirationDate;

    @Column(name = "PAYMENT_DATE")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDate paymentDate;

    public Expense(LocalDate expirationDate, LocalDate paymentDate) {
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
