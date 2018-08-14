package br.com.facility.model.enuns;

import br.com.facility.exceptions.InvalidParameterException;
import br.com.facility.util.Messages;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

public enum PaymentType {

    MONEY("payment.type.money"),
    DEBIT("payment.type.debit"),
    CREDIT("payment.type.credit"),
    BANK_SLIP("payment.type.bank.slip"),
    CHECK("payment.type.check");
    //TODO ADICIONAR TIPO: TRANSFERENCIA BANCÁRIA

    private String paymentName;

    PaymentType(String paymentName) {
       this.paymentName = paymentName;
    }

    public String getPaymentName(){
        return Messages.getMessage(paymentName);
    }

    @JsonCreator
    public static PaymentType fromValue(String paymentName) {
        return Arrays.stream(PaymentType.values())
                .filter(paymentType -> paymentType.name().equals(paymentName.toUpperCase()))
                .findFirst()
                .orElseThrow(() -> new InvalidParameterException(""));
    }
}
