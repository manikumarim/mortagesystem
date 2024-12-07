package com.interview.hcl.banking.mortgagesystem.repository;

import com.interview.hcl.banking.mortgagesystem.model.TransferStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferStatusRepository  extends JpaRepository<TransferStatus,Long> {
}

