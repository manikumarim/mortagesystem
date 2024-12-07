package com.interview.hcl.banking.mortgagesystem.repository;

import com.interview.hcl.banking.mortgagesystem.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionsRepository extends JpaRepository<Transaction,Long> {
}
