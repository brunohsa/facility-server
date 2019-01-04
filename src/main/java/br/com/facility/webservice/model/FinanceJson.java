package br.com.facility.webservice.model;

import br.com.facility.model.enuns.FinanceStatus;
import br.com.facility.model.enuns.PaymentType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class FinanceJson {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigDecimal value = BigDecimal.ZERO;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime releaseDate;

    private String description;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String observation;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PaymentType paymentType;

    private FinanceStatus status;

    public FinanceJson() {
        this.releaseDate = LocalDateTime.now();
    }

    public FinanceJson(BigDecimal value, LocalDateTime releaseDate, String description, String observation, PaymentType paymentType, FinanceStatus status) {
        this.value = value;
        this.releaseDate = releaseDate;
        this.description = description;
        this.observation = observation;
        this.paymentType = paymentType;
        this.status = status;
    }

    public BigDecimal getValue() {
        return value;
    }

    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }

    public String getDescription() {
        return description;
    }

    public String getObservation() {
        return observation;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public FinanceStatus getStatus() {
        return status;
    }
}
