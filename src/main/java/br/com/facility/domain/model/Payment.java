package br.com.facility.domain.model;

import br.com.facility.model.enuns.PaymentType;
import br.com.facility.model.enuns.StatusFinance;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public class Payment implements IPayment {

    private LocalDateTime releaseDate;
    private LocalDate paymentDate;
    private PaymentType paymentType;
    private StatusFinance statusFinance;
    private Value value;

    public Payment(LocalDateTime releaseDate, PaymentType paymentType, StatusFinance statusFinance, Value value, LocalDate paymentDate) {
        this.statusFinance = Optional.ofNullable(statusFinance).orElseThrow(RuntimeException::new);
        this.releaseDate = Optional.ofNullable(releaseDate).orElseThrow(RuntimeException::new);
        this.paymentType = Optional.ofNullable(paymentType).orElseThrow(RuntimeException::new);
        this.value = Optional.ofNullable(value).orElseThrow(RuntimeException::new);
        this.paymentDate = paymentDate;
        this.validate(statusFinance, paymentDate);
    }

    private void validate(StatusFinance statusFinance, LocalDate paymentDate) {
        if(statusFinance.equals(StatusFinance.PAID) && paymentDate == null) {
            throw new RuntimeException("Data de Pagamento inválida !");
        }
        if(!statusFinance.equals(StatusFinance.PAID) && paymentDate != null) {
            throw new RuntimeException("Data de pagamento disponível apenas para despesas paga.");
        }
    }

    @Override
    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }

    @Override
    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    @Override
    public PaymentType getPaymentType() {
        return paymentType;
    }

    @Override
    public StatusFinance getStatus() {
        return statusFinance;
    }

    @Override
    public Value getValue() {
        return value;
    }
}
