package com.remittancemiddleware.remittancemiddleware.dao;

import com.remittancemiddleware.remittancemiddleware.entity.transaction.RemittanceTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RemittanceTransactionDAO extends JpaRepository<RemittanceTransaction, Integer> {
}