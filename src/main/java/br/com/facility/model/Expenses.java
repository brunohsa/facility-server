package br.com.facility.model;

import java.util.Date;

import br.com.facility.model.enuns.StatusExpenses;

public class Expenses {

    private Long id;

    private Date expirationDate;

    private Date paymentDate;

    private Finances finances;

    private StatusExpenses status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Finances getFinances() {
        return finances;
    }

    public void setFinances(Finances finances) {
        this.finances = finances;
    }

    public StatusExpenses getStatus() {
        return status;
    }

    public void setStatus(StatusExpenses status) {
        this.status = status;
    }
}
