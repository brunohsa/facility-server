package br.com.facility.json;

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

    private BigDecimal value;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime releaseDate;

    private String description;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String observation;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String paymentType;

    private String status;

    public FinanceJson() {
    }

    public FinanceJson(BigDecimal value, LocalDateTime releaseDate, String description, String observation, String paymentType, String status) {
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

    public String getPaymentType() {
        return paymentType;
    }

    public String getStatus() {
        return status;
    }
}
