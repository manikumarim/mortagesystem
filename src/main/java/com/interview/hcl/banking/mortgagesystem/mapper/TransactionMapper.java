package com.interview.hcl.banking.mortgagesystem.mapper;

import com.interview.hcl.banking.mortgagesystem.dto.TransactionDto;
import com.interview.hcl.banking.mortgagesystem.model.TransferStatus;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

    public TransactionDto toDto(TransferStatus transaction){
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setTransaction_ref(String.valueOf(transaction.getId()));
        transactionDto.setStatus(transaction.getStatus());
        return transactionDto;
    }
}
