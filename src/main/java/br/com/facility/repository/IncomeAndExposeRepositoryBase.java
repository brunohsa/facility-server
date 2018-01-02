package br.com.facility.repository;

import br.com.facility.model.enuns.FinanceStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface IncomeAndExposeRepositoryBase<E, Long extends Serializable> extends CrudRepository<E, Long> {

    List<E> getByStatusAndUserUsername(@Param("status") FinanceStatus statusFinance, @Param("username") String username);

    List<E> findAllByUserUsername(@Param("username") String username);

    void deleteByUserUsername(@Param("username") String username);

    void deleteByIdAndUserUsername(@Param("id") Long id, @Param("username") String username);

    Optional<E> findByIdAndUserUsername(@Param("id") Long id, @Param("username") String username);
}
