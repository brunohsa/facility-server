package br.com.facility.json;

import br.com.facility.model.Expense;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExpenseJson extends FinanceJson {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate expirationDate;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate paymentDate;

    public ExpenseJson() {
    }

    public ExpenseJson(Expense expense) {
        super(expense.getValue(), expense.getReleaseDate(), expense.getUser().getId(), expense.getDescription(), expense.getObservation(),
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
