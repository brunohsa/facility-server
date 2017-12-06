package br.com.facility.model.enuns;

import br.com.facility.util.Messages;

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
}
