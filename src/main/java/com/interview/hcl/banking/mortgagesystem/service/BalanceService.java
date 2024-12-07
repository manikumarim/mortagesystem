package com.interview.hcl.banking.mortgagesystem.service;

import com.interview.hcl.banking.mortgagesystem.model.Balance;

public interface BalanceService {

    Balance getBalances(Long accountId);

}
