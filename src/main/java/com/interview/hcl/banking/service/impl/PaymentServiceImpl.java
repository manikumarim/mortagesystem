package com.interview.hcl.banking.service.impl;

import com.interview.hcl.banking.mortgagesystem.dto.TransactionDto;
import com.interview.hcl.banking.mortgagesystem.mapper.TransactionMapper;
import com.interview.hcl.banking.mortgagesystem.model.Account;
import com.interview.hcl.banking.mortgagesystem.model.Transaction;
import com.interview.hcl.banking.mortgagesystem.model.TransferStatus;
import com.interview.hcl.banking.mortgagesystem.repository.AccountRepository;
import com.interview.hcl.banking.mortgagesystem.repository.TransactionsRepository;
import com.interview.hcl.banking.mortgagesystem.repository.TransferStatusRepository;
import com.interview.hcl.banking.mortgagesystem.service.PaymentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    TransactionsRepository transactionsRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransferStatusRepository transferStatusRepository;

    @Autowired
    TransactionMapper transactionMapper;

    @Transactional
    @Override
    public TransactionDto transferFunds(Long fromAccountId, Long toAccountId, String description, BigDecimal amount) {
        Account fromAccount = accountRepository.getReferenceById(fromAccountId);
        Transaction transaction = new Transaction();
        transaction.setAccount(fromAccount);

        Account toAccount = accountRepository.getReferenceById(toAccountId);
        transaction.setAccount2(toAccount);
        transaction.setDescription(description);
        transaction.setAmount(amount);
        transaction.setTimestamp(new Timestamp(new Date().getTime()).toLocalDateTime());
        Transaction transactionResult = transactionsRepository.save(transaction);

        //TO-DO : Need to change into the EnumType
        TransferStatus transferStatus = new TransferStatus();
        transferStatus.setStatus("CONFIRMED");
        transferStatus.setTransaction(transactionResult);
        transferStatus.setDateTime(new Timestamp(new Date().getTime()).toLocalDateTime());

        List<TransferStatus> transferStatuses = new ArrayList<>();
        transferStatuses.add(transferStatus);
        TransactionDto transactionDto = new TransactionDto();
        TransferStatus transferStatusResult = transferStatusRepository.save(transferStatus);

        transactionDto = transactionMapper.toDto(transferStatusResult);
        return transactionDto;
    }
}
