package br.com.facility.repository;

import br.com.facility.model.Finance;
import org.springframework.data.repository.CrudRepository;

public interface FinanceRepository extends CrudRepository<Finance, Long> {
}
