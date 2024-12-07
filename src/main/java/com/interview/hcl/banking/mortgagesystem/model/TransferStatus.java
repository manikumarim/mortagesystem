package com.interview.hcl.banking.mortgagesystem.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="transactions_status")

public class TransferStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="transaction_id" , nullable=false)
    private Transaction transaction;

    private String status;

    private LocalDateTime dateTime;

    public TransferStatus(){

    }
    public TransferStatus(Long id, Transaction transaction, String status, LocalDateTime dateTime) {
        this.id = id;
        this.transaction = transaction;
        this.status = status;
        this.dateTime = dateTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        TransferStatus that = (TransferStatus) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
