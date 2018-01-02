package br.com.facility.model.enuns;

import br.com.facility.util.Messages;

public enum FinanceStatus {

	PAID("status.finance.paid"),
	PENDING("status.finance.pending"),
	OVERDUE("status.finance.overdue"),
	CANCELLED("status.finance.cancelled"),

	//TODO REMOVER
	EXPIRED("status.finance.overdue");

	private String financeStatusName;

	FinanceStatus(String financeStatusName) {
		this.financeStatusName = financeStatusName;
	}

	public String getStatusName() {
		return Messages.getMessage(financeStatusName);
	}
}
