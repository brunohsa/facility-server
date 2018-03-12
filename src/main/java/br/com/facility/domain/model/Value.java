package br.com.facility.domain.model;

import java.math.BigDecimal;

public class Value {

    private final BigDecimal value;

    public Value(BigDecimal value) {
        this.validate(value);
        this.value = value;
    }

    private void validate(BigDecimal value) {
        if (value.compareTo(BigDecimal.ZERO) <= 0) {
            //valor não pode ser zerado nem negativo
            throw new RuntimeException();
        }
    }

    public BigDecimal getValue() {
        return value;
    }
}
