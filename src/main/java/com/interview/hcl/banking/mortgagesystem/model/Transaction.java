package com.interview.hcl.banking.mortgagesystem.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * To provide the model for the transaction
 */
@Entity
@Table(name="transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Account.class)
    @JoinColumn(name = "fromAccountId",  nullable = false)
    private  Account account;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Account.class)
    @JoinColumn(name="toAccountId" ,nullable=false)
    private Account account2;

    @Column(nullable=false)
    private BigDecimal amount;

    @Column(name="description")
    private String description;


    @Column(name="date_time" ,nullable=false)
    private LocalDateTime timestamp;

    @OneToMany(mappedBy ="transaction", cascade = CascadeType.ALL)
    private List<TransferStatus> transferStatuses;

    public Transaction() {}

    public Transaction(Long id, Long fromAccountId, Long toAccountId, BigDecimal amount, LocalDateTime timestamp, List<TransferStatus> transferStatuses) {
        this.id = id;
        this.account = new Account();
        account.setId(fromAccountId);
        this.account2 = new Account();
        account2.setId(toAccountId);
        this.amount = amount;
        this.timestamp = timestamp;
        this.transferStatuses = transferStatuses;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<TransferStatus> getTransferStatuses() {
        return transferStatuses;
    }

    public void setTransferStatuses(List<TransferStatus> transferStatuses) {
        this.transferStatuses = transferStatuses;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getAccount2() {
        return account2;
    }

    public void setAccount2(Account account2) {
        this.account2 = account2;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Transaction that = (Transaction) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
