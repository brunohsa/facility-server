package br.com.facility.repository;

import br.com.facility.model.Income;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeRepository extends IncomeAndExposeRepositoryBase<Income, Long> {

}
