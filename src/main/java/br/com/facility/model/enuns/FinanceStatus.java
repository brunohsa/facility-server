package br.com.facility.model.enuns;

import br.com.facility.exceptions.ErrorMessages;
import br.com.facility.exceptions.InvalidParameterException;
import br.com.facility.util.Messages;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum FinanceStatus {

	PENDING("status.finance.pending") {
		@Override
		public List<FinanceStatus> getAvaliableSituations() {
			return Arrays.asList(FinanceStatus.PAID, FinanceStatus.CANCELLED, FinanceStatus.OVERDUE);
		}
	},
	PAID("status.finance.paid") {
		@Override
		public List<FinanceStatus> getAvaliableSituations() {
			return Arrays.asList(FinanceStatus.CANCELLED);
		}
	},
	OVERDUE("status.finance.overdue") {
		@Override
		public List<FinanceStatus> getAvaliableSituations() {
			return Arrays.asList(FinanceStatus.PAID, FinanceStatus.CANCELLED);
		}
	},
	CANCELLED("status.finance.cancelled") {
		@Override
		public List<FinanceStatus> getAvaliableSituations() {
			return new ArrayList<>();
		}
	};

	private String situationName;

	FinanceStatus(String situationName) {
		this.situationName = situationName;
	}

	public String getSituationName() {
		return Messages.getMessage(this.situationName);
	}

	public abstract List<FinanceStatus> getAvaliableSituations();

	@JsonCreator
	public static FinanceStatus fromValue(String financeSituation) {
		return Arrays.stream(FinanceStatus.values())
				.filter(situation -> situation.name().equals(financeSituation.toUpperCase()))
				.findFirst()
				.orElseThrow(() -> new InvalidParameterException(ErrorMessages.INVALLID_FINANCE_SITUATION));
	}

	public boolean canChangeTo(FinanceStatus newStatus) {
		return getAvaliableSituations().contains(newStatus);
	}
}
