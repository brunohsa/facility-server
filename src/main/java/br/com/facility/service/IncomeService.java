package br.com.facility.service;

import br.com.facility.model.Income;
import br.com.facility.repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncomeService extends GenericService<Income, IncomeRepository> implements IIncomeService {

    @Autowired
    private IncomeRepository incomeRepository;
}
