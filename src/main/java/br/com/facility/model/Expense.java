package br.com.facility.model;

import br.com.facility.model.enuns.StatusExpenses;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity(name = "EXPENSE")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "EXPIRATION_DATE")
    private LocalDate expirationDate;

    @Column(name = "PAYMENT_DATE")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDate paymentDate;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "FINANCE_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_EXPENSE_FINANCE"))
    @NotNull
    private Finance finance;

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

    public Finance getFinance() {
        return finance;
    }

    public void setFinance(Finance finance) {
        this.finance = finance;
    }

    public StatusExpenses getStatus() {
        return status;
    }

    public void setStatus(StatusExpenses status) {
        this.status = status;
    }
}
