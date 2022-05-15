package com.blusalt.billingservice.repository.internal;

import com.blusalt.billingservice.model.internal.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
