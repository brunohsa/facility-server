package br.com.facility.model.enuns;

public enum StatusFinance {

	PAID("status.finance.paid"),
	PENDING("status.finance.pending"),
	EXPIRED("status.finance.expired"),
	CANCELLED("status.finance.cancelled");

	private String statusFinanceName;

	StatusFinance(String statusFinanceName) {
		this.statusFinanceName = statusFinanceName;
	}

	public String getStatusName() {
		return statusFinanceName;
	}
}
