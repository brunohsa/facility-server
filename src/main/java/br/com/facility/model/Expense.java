package br.com.facility.model;

import br.com.facility.model.enuns.PaymentType;
import br.com.facility.model.enuns.StatusFinance;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

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
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate expirationDate;

    @Column(name = "PAYMENT_DATE")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate paymentDate;

    public Expense() {}

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
