package br.com.facility.json.response;

import br.com.facility.json.FinanceJson;
import br.com.facility.model.Expense;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExpenseResponse extends FinanceJson {


    private Long id;

    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate expirationDate;

    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate paymentDate;

    public ExpenseResponse() {
    }

    public ExpenseResponse(Expense expense) {
        super(expense.getValue(), expense.getReleaseDate(), expense.getDescription(), expense.getObservation(),
                expense.getPaymentType(), expense.getStatus());
        this.id = expense.getId();
        this.expirationDate = expense.getExpirationDate();
        this.paymentDate = expense.getPaymentDate();
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
