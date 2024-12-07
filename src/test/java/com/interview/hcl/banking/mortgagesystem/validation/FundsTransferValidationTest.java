package com.interview.hcl.banking.mortgagesystem.validation;

import com.interview.hcl.banking.mortgagesystem.dto.TransactionDto;
import com.interview.hcl.banking.mortgagesystem.model.Balance;
import com.interview.hcl.banking.mortgagesystem.service.AccountService;
import com.interview.hcl.banking.mortgagesystem.service.BalanceService;
import com.interview.hcl.banking.mortgagesystem.validation.FundTransferValidation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class FundTransferValidationTest {

    @Mock
    private AccountService accountService;

    @Mock
    private BalanceService balanceService;

    @InjectMocks
    private FundTransferValidation fundTransferValidation;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testValidTransaction() {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setFromAccountId(1L);
        transactionDto.setToAccountId(2L);
        transactionDto.setAmount(BigDecimal.valueOf(100));

        when(accountService.checkAccount(1L)).thenReturn(true);
        when(accountService.checkAccount(2L)).thenReturn(true);

        Balance balance = new Balance();
        balance.setBalance(BigDecimal.valueOf(500));
        when(balanceService.getBalances(1L)).thenReturn(balance);

        List<String> errors = fundTransferValidation.checkValidation(transactionDto);
        assertEquals(0, errors.size());
    }

    @Test
    void testInvalidFromAccount() {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setFromAccountId(1L);
        transactionDto.setToAccountId(2L);
        transactionDto.setAmount(BigDecimal.valueOf(100));

        when(accountService.checkAccount(1L)).thenReturn(true);
        Balance balance = new Balance();
        balance.setBalance(BigDecimal.valueOf(500));
        when(balanceService.getBalances(1L)).thenReturn(balance);

        List<String> errors = fundTransferValidation.checkValidation(transactionDto);
        System.out.println(errors);
        assertEquals(1, errors.size());


    }

    @Test
    void testInsufficientBalance() {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setFromAccountId(1L);
        transactionDto.setToAccountId(2L);
        transactionDto.setAmount(BigDecimal.valueOf(1000));

        when(accountService.checkAccount(1L)).thenReturn(true);
        when(accountService.checkAccount(2L)).thenReturn(true);

        Balance balance = new Balance();
        balance.setBalance(BigDecimal.valueOf(500));
        when(balanceService.getBalances(1L)).thenReturn(balance);

        List<String> errors = fundTransferValidation.checkValidation(transactionDto);
        System.out.println(errors);
    }

    @Test
    void testSameFromAndToAccount() {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setFromAccountId(1L);
        transactionDto.setToAccountId(1L);
        transactionDto.setAmount(BigDecimal.valueOf(100));

        when(accountService.checkAccount(1L)).thenReturn(true);
        Balance balance = new Balance();
        balance.setBalance(BigDecimal.valueOf(500));
        when(balanceService.getBalances(1L)).thenReturn(balance);

        List<String> errors = fundTransferValidation.checkValidation(transactionDto);

        System.out.println(errors);

    }
}
