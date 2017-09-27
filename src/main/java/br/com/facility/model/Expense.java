package br.com.facility.model;

import br.com.facility.model.enuns.StatusExpenses;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    @NotNull
    private StatusExpenses status;

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

    public StatusExpenses getStatus() {
        return status;
    }

    public void setStatus(StatusExpenses status) {
        this.status = status;
    }
}
