package com.remittancemiddleware.remittancemiddleware.dao;

import com.remittancemiddleware.remittancemiddleware.entity.transaction.RemittanceTransaction;
import com.remittancemiddleware.remittancemiddleware.entity.enumdata.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RemittanceTransactionDAO extends JpaRepository<RemittanceTransaction, Integer> {


    List<RemittanceTransaction> findByTransactionStatusAndCompanyId(TransactionStatus status,int id);



}