package com.remittancemiddleware.remittancemiddleware.service;

import com.remittancemiddleware.remittancemiddleware.entity.User;
import com.remittancemiddleware.remittancemiddleware.entity.enumdata.TransactionStatus;
import com.remittancemiddleware.remittancemiddleware.entity.transaction.RemittanceTransaction;


import java.util.List;

public interface RemittanceTransactionService {
    List<RemittanceTransaction> findByTransactionStatusAndCompanyId(TransactionStatus status, int companyId);
    List<RemittanceTransaction> findByCompanyId(int userId);
    TransactionStatus getTransactionStatus(String status);
}
