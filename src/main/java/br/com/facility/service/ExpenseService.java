package br.com.facility.service;

import br.com.facility.model.Expense;
import br.com.facility.model.enuns.FinanceSituation;
import br.com.facility.repository.ExpenseRepository;
import br.com.facility.exceptions.FinanceNotFoundException;
import br.com.facility.exceptions.InvalidParameterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

	@Autowired
	private ExpenseRepository repository;

	public List<Expense> getExpensesByStatus(FinanceSituation status) {
		return repository.getBySituationAndUserUsername(status, getLoggedUser());
	}

	public List<Expense> filterExpensesByDate(LocalDate date) {
		LocalDateTime dateTime = LocalDateTime.of(date, LocalTime.MIDNIGHT);
		return repository.filterExpensesByDate(dateTime, getLoggedUser());
	}

	public Expense save(Expense entity) {
		if (entity == null) {
			throw new InvalidParameterException("Despesa não deve ser nula.");
		}
		if(StringUtils.isEmpty(entity.getDescription())) {
			throw new InvalidParameterException("O campo Descrição é obrigatório.");
		}
		if (entity.getPaymentType() == null) {
			throw new InvalidParameterException("Tipo de pagamento inválido");
		}
		if (entity.getSituation() == null) {
			throw new InvalidParameterException("Status da despesa inválido");
		}
		if (entity.getValue().compareTo(BigDecimal.ZERO) == 0 || entity.getValue().compareTo(BigDecimal.ZERO) == -1) {
			throw new InvalidParameterException("Valor da despesa deve ser maior do que zero.");
		}
		if(entity.isPayd() && entity.getPaymentDate() == null) {
			throw new InvalidParameterException("Despesas pagas devem possuir data de pagamento.");
		}
		if(!entity.isPayd() && entity.getPaymentDate() != null) {
			throw new InvalidParameterException("Data de pagamento deve ser preenchida apenas para despesas pagas.");
		}
		return repository.save(entity);
	}

	public Expense findById(Long id) {
		if(id == null) {
			throw new InvalidParameterException("Por favor, informe um id.");
		}
		Optional<Expense> finance = repository.findByIdAndUserUsername(id, getLoggedUser());
		return finance.orElseThrow(() -> new FinanceNotFoundException());
	}

	public List<Expense> findAll() {
		return repository.findAllByUserUsername(getLoggedUser());
	}

	public void delete(Long id) {
		if(id == null) {
			throw new InvalidParameterException("Por favor, informe um id.");
		}
		repository.delete(id);
	}


	public void deleteAll() {
		repository.deleteAll();
	}

	public void update() {

	}

	public String getLoggedUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.getName();
	}
}
