package com.interview.hcl.banking.mortgagesystem.service;

import com.interview.hcl.banking.mortgagesystem.dto.TransactionDto;

import java.math.BigDecimal;

public interface PaymentService {

    TransactionDto transferFunds(Long fromAccountId, Long toAccountId, String description, BigDecimal amount);

}
