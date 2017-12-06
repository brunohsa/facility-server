package br.com.facility.service;

import br.com.facility.model.enuns.StatusFinance;
import br.com.facility.repository.IncomeAndExposeRepositoryBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class IncomeAndExpenseServiceBase<E, T extends IncomeAndExposeRepositoryBase> {

    @Autowired
    private T repository;

    public List<E> getByStatus(StatusFinance statusFinance) {
        return repository.getByStatusAndUserUsername(statusFinance, getLoggedUser());
    }

    public List<E> findAll() {
        return repository.findAllByUserUsername(getLoggedUser());
    }

    public void delete(Long id){
        repository.deleteByIdAndUserUsername(id, getLoggedUser());
    }

    public void deleteAll() {
        repository.deleteByUserUsername(getLoggedUser());
    }

    public E save(E entity) {
        return (E) repository.save(entity);
    }

    public E findById(Long id){
        return (E) repository.findByIdAndUserUsername(id, getLoggedUser());
    }

    public String getLoggedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
}
