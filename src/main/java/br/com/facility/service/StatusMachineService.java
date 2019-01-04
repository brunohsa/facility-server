package br.com.facility.service;

import br.com.facility.model.Finance;
import br.com.facility.model.enuns.FinanceStatus;
import br.com.facility.repository.FinanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class StatusMachineService {

    @Autowired
    private FinanceRepository financeRepository;

    public Finance toPaid(Finance finance) throws IllegalStateException {
        finance.setPaymentDate(LocalDate.now());
        changeStatus(finance, FinanceStatus.PAID);
        return finance;
    }

    public Finance toCancelled(Finance finance) throws IllegalStateException {
        if (finance.isPaid()) {
            finance.setPaymentDate(null);
        }
        changeStatus(finance, FinanceStatus.CANCELLED);
        return finance;
    }

    public Finance toOverdue(Finance finance) throws IllegalStateException {
        changeStatus(finance, FinanceStatus.OVERDUE);
        return finance;
    }

    private void changeStatus(Finance finance, FinanceStatus newState) throws IllegalStateException {
        if (!finance.getStatus().canChangeTo(newState)) {
            throw new IllegalStateException();
        }
        finance.setStatus(newState);
        financeRepository.save(finance);
    }
}
