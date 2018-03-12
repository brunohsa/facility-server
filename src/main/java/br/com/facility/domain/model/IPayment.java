package br.com.facility.domain.model;

import br.com.facility.model.enuns.PaymentType;
import br.com.facility.model.enuns.StatusFinance;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface IPayment {

	LocalDateTime getReleaseDate();

	LocalDate getPaymentDate();

	PaymentType getPaymentType();

	StatusFinance getStatus();

	Value getValue();
}
