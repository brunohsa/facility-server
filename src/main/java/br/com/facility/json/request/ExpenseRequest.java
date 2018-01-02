package br.com.facility.json.request;

import br.com.facility.json.FinanceJson;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExpenseRequest extends FinanceJson {

    private Long id;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate expirationDate;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate paymentDate;

    public ExpenseRequest() {
    }

    public ExpenseRequest(BigDecimal value, LocalDateTime releaseDate, String description, String observation, String paymentType, String status, Long id,
            LocalDate expirationDate, LocalDate paymentDate) {
        super(value, releaseDate, description, observation, paymentType, status);
        this.id = id;
        this.expirationDate = expirationDate;
        this.paymentDate = paymentDate;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }
}
