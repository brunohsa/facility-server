package br.com.facility.repository;

import br.com.facility.model.Income;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeRepository extends CrudRepository<Income, Long> {

    void deleteByUserId(@Param("id") Long userId);
}
