package br.com.facility.webservice.model.response;

import br.com.facility.model.enuns.FinanceSituation;
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

    private List<FinanceSituation> avaliableSituations;

    public ExpenseResponse() {
    }

    public ExpenseResponse(BigDecimal value, String description, String observation, PaymentType paymentType, FinanceSituation status, Long id, LocalDateTime releaseDate, List<FinanceSituation> avaliableSituations) {
        super(value, releaseDate, description, observation, paymentType, status);
        this.id = id;
        this.avaliableSituations = avaliableSituations;
    }

    public Long getId() {
        return id;
    }

    public List<FinanceSituation> getAvaliableSituations() {
        return avaliableSituations;
    }
}
