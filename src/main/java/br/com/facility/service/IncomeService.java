package br.com.facility.service;

import br.com.facility.model.Income;
import br.com.facility.repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class IncomeService extends GenericService<Income> implements IIncomeService {

    @Autowired
    private IncomeRepository incomeRepository;

    @Override
    protected CrudRepository getRepository() {
        return incomeRepository;
    }
}
