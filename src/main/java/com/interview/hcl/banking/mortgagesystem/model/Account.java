package com.interview.hcl.banking.mortgagesystem.model;

import jakarta.persistence.*;

@Entity
@Table(name="account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="accountnumber")
    private String accountNumber;

    public Account() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;
        return getId().equals(account.getId()) && getAccountNumber().equals(account.getAccountNumber());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getAccountNumber().hashCode();
        return result;
    }
}
