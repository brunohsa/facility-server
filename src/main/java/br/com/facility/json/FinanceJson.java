package br.com.facility.json;

import br.com.facility.model.enuns.PaymentType;
import br.com.facility.model.enuns.StatusFinance;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FinanceJson {

    private BigDecimal value;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime releaseDate;

    private Long userId;

    private String description;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String observation;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PaymentType paymentType;

    private StatusFinance status;

    public FinanceJson() {
        this.releaseDate = LocalDateTime.now();
    }

    public FinanceJson(BigDecimal value, LocalDateTime releaseDate, Long userId, String description, String observation, PaymentType paymentType, StatusFinance status) {
        this.value = value;
        this.releaseDate = releaseDate;
        this.userId = userId;
        this.description = description;
        this.observation = observation;
        this.paymentType = paymentType;
        this.status = status;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDateTime releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public StatusFinance getStatus() {
        return status;
    }

    public void setStatus(StatusFinance status) {
        this.status = status;
    }
}
