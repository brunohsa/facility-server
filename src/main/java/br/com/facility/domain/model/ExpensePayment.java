package br.com.facility.domain.model;

import br.com.facility.model.enuns.PaymentType;
import br.com.facility.model.enuns.StatusFinance;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public class ExpensePayment implements IPayment {

	private Payment payment;
	private LocalDate expirationDate;

	public ExpensePayment(Payment payment, LocalDate expirationDate) {
		this.payment = Optional.ofNullable(payment).orElseThrow(RuntimeException::new);
		this.expirationDate = expirationDate;
	}

	public LocalDate getExpirationDate() {
		return expirationDate;
	}

	@Override
	public LocalDate getPaymentDate() {
		return payment.getPaymentDate();
	}

	@Override
	public LocalDateTime getReleaseDate() {
		return payment.getReleaseDate();
	}

	@Override
	public PaymentType getPaymentType() {
		return payment.getPaymentType();
	}

	@Override
	public StatusFinance getStatus() {
		return payment.getStatus();
	}

	@Override
	public Value getValue() {
		return payment.getValue();
	}
}
