package br.com.facility.webservice.model.response;

import br.com.facility.model.enuns.FinanceStatus;
import br.com.facility.model.enuns.PaymentType;
import br.com.facility.webservice.model.FinanceJson;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExpenseResponse extends FinanceJson {

    private Long id;

    private List<FinanceStatus> avaliableSituations;

    public ExpenseResponse() {
    }

    public ExpenseResponse(BigDecimal value, String description, String observation, PaymentType paymentType, FinanceStatus situation, Long id, LocalDateTime releaseDate, List<FinanceStatus> avaliableSituations) {
        super(value, releaseDate, description, observation, paymentType, situation);
        this.id = id;
        this.avaliableSituations = avaliableSituations;
    }

    public Long getId() {
        return id;
    }

    public List<FinanceStatus> getAvaliableSituations() {
        return avaliableSituations;
    }
}
