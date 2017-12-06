package br.com.facility.model.enuns;

import br.com.facility.util.Messages;

public enum StatusFinance {

	PAID("status.finance.paid"),
	PENDING("status.finance.pending"),
	OVERDUE("status.finance.overdue"),
	CANCELLED("status.finance.cancelled"),

	//TODO REMOVER
	EXPIRED("status.finance.overdue");

	private String statusFinanceName;

	StatusFinance(String statusFinanceName) {
		this.statusFinanceName = statusFinanceName;
	}

	public String getStatusName() {
		return Messages.getMessage(statusFinanceName);
	}
}
