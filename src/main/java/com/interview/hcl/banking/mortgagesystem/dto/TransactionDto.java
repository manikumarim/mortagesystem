package com.interview.hcl.banking.mortgagesystem.dto;

import com.interview.hcl.banking.mortgagesystem.model.Transaction;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class TransactionDto {
    private String transaction_ref;
    @NotNull (message="FromAccount Cannot be Null")
    private Long fromAccountId;
    @NotNull(message="ToAccount Cannot be Null")
    private Long toAccountId;
    @NotNull
    @Positive(message="Amount Cannot be Null and Positive")
    private BigDecimal amount;
    private String status;
    private String description;

    public String getTransaction_ref() {
        return transaction_ref;
    }

    public void setTransaction_ref(String transaction_ref) {
        this.transaction_ref = transaction_ref;
    }

    public Long getFromAccountId() {
        return fromAccountId;
    }

    public void setFromAccountId(Long fromAccountId) {
        this.fromAccountId = fromAccountId;
    }

    public Long getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(Long toAccountId) {
        this.toAccountId = toAccountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private TransactionDto convertToDto(Transaction transaction) {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setTransaction_ref(String.valueOf(transaction.getId()));
       // transactionDto.setStatus(transaction.getTransferStatuses().getLast());
        return transactionDto;
    }
}
