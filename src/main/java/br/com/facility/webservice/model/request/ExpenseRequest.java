package br.com.facility.webservice.model.request;

import br.com.facility.webservice.model.FinanceJson;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExpenseRequest extends FinanceJson {

    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate expirationDate;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate paymentDate;

    public ExpenseRequest() {
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }
}
