package br.com.facility.model.enuns;

import br.com.facility.exceptions.InvalidParameterException;
import br.com.facility.util.Messages;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum FinanceSituation {

	PENDING("status.finance.pending") {
		@Override
		public List<FinanceSituation> getAvaliableSituations() {
			return Arrays.asList(FinanceSituation.PAID, FinanceSituation.CANCELLED, FinanceSituation.OVERDUE);
		}
	},
	PAID("status.finance.paid") {
		@Override
		public List<FinanceSituation> getAvaliableSituations() {
			return Arrays.asList(FinanceSituation.CANCELLED);
		}
	},
	OVERDUE("status.finance.overdue") {
		@Override
		public List<FinanceSituation> getAvaliableSituations() {
			return Arrays.asList(FinanceSituation.PAID, FinanceSituation.CANCELLED);
		}
	},
	CANCELLED("status.finance.cancelled") {
		@Override
		public List<FinanceSituation> getAvaliableSituations() {
			return new ArrayList<>();
		}
	};

	private String situationName;

	FinanceSituation(String situationName) {
		this.situationName = situationName;
	}

	public String getSituationName() {
		return Messages.getMessage(this.situationName);
	}

	public abstract List<FinanceSituation> getAvaliableSituations();

	@JsonCreator
	public static FinanceSituation fromValue(String financeSituation) {
		return Arrays.stream(FinanceSituation.values())
				.filter(situation -> situation.name().equals(financeSituation.toUpperCase()))
				.findFirst()
				.orElseThrow(() -> new InvalidParameterException("Situação da financa invalida."));
	}
}
