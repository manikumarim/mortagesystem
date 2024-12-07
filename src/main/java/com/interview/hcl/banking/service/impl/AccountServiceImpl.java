package com.interview.hcl.banking.service.impl;

import com.interview.hcl.banking.mortgagesystem.model.Account;
import com.interview.hcl.banking.mortgagesystem.repository.AccountRepository;
import com.interview.hcl.banking.mortgagesystem.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Boolean checkAccount(Long accountId){
        Optional<Account> account = accountRepository.findById(accountId);
        if(account.isPresent() && !account.isEmpty()){return true;}
        else{return false;}

    }
}
