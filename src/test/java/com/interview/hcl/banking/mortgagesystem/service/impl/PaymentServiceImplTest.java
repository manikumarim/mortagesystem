package com.interview.hcl.banking.service.impl;

import com.interview.hcl.banking.mortgagesystem.dto.TransactionDto;
import com.interview.hcl.banking.mortgagesystem.mapper.TransactionMapper;
import com.interview.hcl.banking.mortgagesystem.model.Account;
import com.interview.hcl.banking.mortgagesystem.model.Transaction;
import com.interview.hcl.banking.mortgagesystem.model.TransferStatus;
import com.interview.hcl.banking.mortgagesystem.repository.AccountRepository;
import com.interview.hcl.banking.mortgagesystem.repository.TransactionsRepository;
import com.interview.hcl.banking.mortgagesystem.repository.TransferStatusRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.sql.SQLDataException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PaymentServiceImplTest {

    @Mock
    private TransactionsRepository transactionsRepository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private TransferStatusRepository transferStatusRepository;

    @Mock
    private TransactionMapper transactionMapper;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTransferFunds() {
        // Mock input values
        Long fromAccountId = 1L;
        Long toAccountId = 2L;
        String description = "ABC";
        BigDecimal amount = BigDecimal.valueOf(1000);

        // Mock account data
        Account fromAccount = new Account();
        fromAccount.setId(fromAccountId);
        fromAccount.setAccountNumber("XXXX");

        Account toAccount = new Account();
        toAccount.setId(toAccountId);
        toAccount.setAccountNumber("XXX1");

        // Mock transaction object
        Transaction transaction = new Transaction();
        transaction.setId(100L);

        // Mock transfer status object
        TransferStatus transferStatus = new TransferStatus();
        transferStatus.setId(200L);
        transferStatus.setStatus("CONFIRMED");
        transferStatus.setDateTime(LocalDateTime.now());

        // Mock DTO
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setTransaction_ref(new String("200"));

        // Mock behavior
        when(accountRepository.getReferenceById(fromAccountId)).thenReturn(fromAccount);
        when(accountRepository.getReferenceById(toAccountId)).thenReturn(toAccount);
        when(transactionsRepository.save(any(Transaction.class))).thenReturn(transaction);
        when(transferStatusRepository.save(any(TransferStatus.class))).thenReturn(transferStatus);
        when(transactionMapper.toDto(any(TransferStatus.class))).thenReturn(transactionDto);

        // Execute method
        TransactionDto result = paymentService.transferFunds(fromAccountId, toAccountId, description, amount);

        // Verify behavior
        verify(accountRepository, times(1)).getReferenceById(fromAccountId);
        verify(accountRepository, times(1)).getReferenceById(toAccountId);
        verify(transactionsRepository, times(1)).save(any(Transaction.class));
        verify(transferStatusRepository, times(1)).save(any(TransferStatus.class));
        verify(transactionMapper, times(1)).toDto(any(TransferStatus.class));

        // Assert results
        assertEquals(transactionDto, result);
        assertEquals(transactionDto.getTransaction_ref(), result.getTransaction_ref());
        // Capture argument for additional verification if needed
        ArgumentCaptor<Transaction> transactionCaptor = ArgumentCaptor.forClass(Transaction.class);
        verify(transactionsRepository).save(transactionCaptor.capture());
        assertEquals(description, transactionCaptor.getValue().getDescription());
        assertEquals(amount, transactionCaptor.getValue().getAmount());
    }


    @Test
    void testTransferFundsStatusWhenAccountNotFound() {
        // Mock input values
        Long fromAccountId = 1L;
        Long toAccountId = 2L;
        String description = "ABC";
        BigDecimal amount = BigDecimal.valueOf(1000);

        // Mock account data
        Account fromAccount = new Account();
        fromAccount.setId(fromAccountId);
        fromAccount.setAccountNumber("XXXX");

        Account toAccount = new Account();
        toAccount.setId(toAccountId);
        toAccount.setAccountNumber("XXX1");

        // Mock transaction object
        Transaction transaction = new Transaction();
        transaction.setId(100L);

        // Mock transfer status object
        TransferStatus transferStatus = new TransferStatus();
        transferStatus.setId(200L);
        transferStatus.setStatus("CONFIRMED");
        transferStatus.setDateTime(LocalDateTime.now());

        // Mock DTO
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setTransaction_ref(new String("200"));

        // Mock behavior

        when(accountRepository.getReferenceById(toAccountId)).thenReturn(toAccount);
        when(transactionsRepository.save(any(Transaction.class))).thenReturn(transaction);
        when(transferStatusRepository.save(any(TransferStatus.class))).thenReturn(transferStatus);
        when(transactionMapper.toDto(any(TransferStatus.class))).thenReturn(transactionDto);

        // Execute method
        TransactionDto result = paymentService.transferFunds(fromAccountId, toAccountId, description, amount);

        // Verify behavior
        verify(accountRepository, times(1)).getReferenceById(fromAccountId);
        verify(accountRepository, times(1)).getReferenceById(toAccountId);
        verify(transactionsRepository, times(1)).save(any(Transaction.class));
        verify(transferStatusRepository, times(1)).save(any(TransferStatus.class));
        verify(transactionMapper, times(1)).toDto(any(TransferStatus.class));

        // Assert results
        assertEquals(transactionDto, result);
        assertEquals(transactionDto.getTransaction_ref(), result.getTransaction_ref());
        // Capture argument for additional verification if needed
        ArgumentCaptor<Transaction> transactionCaptor = ArgumentCaptor.forClass(Transaction.class);
        verify(transactionsRepository).save(transactionCaptor.capture());
        assertEquals(description, transactionCaptor.getValue().getDescription());
        assertEquals(amount, transactionCaptor.getValue().getAmount());
    }
}
