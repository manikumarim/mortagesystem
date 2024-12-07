package com.interview.hcl.banking.mortgagesystem.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.DateTimeException;
import java.time.LocalDateTime;

@Entity
@Table(name="balance")
public class Balance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="ACCOUNTID" , nullable=false)
    private Account account;

    @Column(name="AMOUNT")
    private BigDecimal balance;

    @Column(name="BALANCE_DATE_TIME")
    private LocalDateTime timestamp;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
