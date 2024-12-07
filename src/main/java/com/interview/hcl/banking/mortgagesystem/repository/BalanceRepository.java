package com.interview.hcl.banking.mortgagesystem.repository;

import com.interview.hcl.banking.mortgagesystem.model.Balance;
import com.interview.hcl.banking.mortgagesystem.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BalanceRepository extends JpaRepository<Balance,Long> {

    @Query("FROM Balance n where n.account.id = ?1 ORDER BY n.timestamp desc")
    List<Balance> findBy(Long accountId);

}
