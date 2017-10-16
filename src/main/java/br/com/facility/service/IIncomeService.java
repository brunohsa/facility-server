package br.com.facility.service;

import br.com.facility.model.Income;

public interface IIncomeService {

	public Income findById(Long id);

	public Income save(Income entity);

	public void delete(Long id);
}
