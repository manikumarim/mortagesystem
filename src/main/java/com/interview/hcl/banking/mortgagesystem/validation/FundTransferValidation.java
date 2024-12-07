package com.interview.hcl.banking.mortgagesystem.validation;

import com.interview.hcl.banking.mortgagesystem.dto.TransactionDto;
import com.interview.hcl.banking.mortgagesystem.model.Balance;
import com.interview.hcl.banking.mortgagesystem.service.AccountService;
import com.interview.hcl.banking.mortgagesystem.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FundTransferValidation {

    @Autowired
    AccountService accountService;

    @Autowired
    BalanceService balanceService;

    //Going forward we can have BusinessErrorResult DTO
    public List<String> checkValidation(TransactionDto transactionDto)
    {
        List<String> errors = new ArrayList<>();
        Boolean checkFromAccount =accountService.checkAccount(transactionDto.getFromAccountId());
        Boolean checkToAccount =accountService.checkAccount(transactionDto.getToAccountId());
        Boolean checkBalance = false;
        if(checkFromAccount){
         checkBalance = checkBalanceValidation(transactionDto);
        }
        if(!checkFromAccount)
        {
            errors.add("From account is not valid");
        }
        if(!checkToAccount) {
            errors.add("To account is not valid");
        }
        if(transactionDto.getFromAccountId() == transactionDto.getToAccountId()){
            errors.add("From Account and To Account are the same");
        }
        if(!checkBalance)
        {
            errors.add("Balance is not valid");
        }
        return errors   ;
    }

    private Boolean checkBalanceValidation(TransactionDto transactionDto){
        Balance fromAccountbalance = balanceService.getBalances(transactionDto.getFromAccountId());
        if(fromAccountbalance.getBalance().compareTo(transactionDto.getAmount()) < 0)
        {
            return false;
        }
        return true;
    }
}
