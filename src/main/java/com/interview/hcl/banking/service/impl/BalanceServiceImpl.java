package com.interview.hcl.banking.service.impl;

import com.interview.hcl.banking.mortgagesystem.model.Balance;
import com.interview.hcl.banking.mortgagesystem.repository.BalanceRepository;
import com.interview.hcl.banking.mortgagesystem.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BalanceServiceImpl implements BalanceService {

    @Autowired
    private BalanceRepository balanceRepository;



    @Override
    public Balance getBalances(Long accountId) {
        return balanceRepository.findBy(accountId).get(0);    }

}
