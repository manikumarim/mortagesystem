package com.interview.hcl.banking.mortgagesystem.repository;

import com.interview.hcl.banking.mortgagesystem.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {
}

