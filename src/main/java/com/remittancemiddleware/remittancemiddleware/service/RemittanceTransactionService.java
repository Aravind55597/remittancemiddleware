package com.remittancemiddleware.remittancemiddleware.service;

import com.remittancemiddleware.remittancemiddleware.entity.enumdata.TransactionStatus;
import com.remittancemiddleware.remittancemiddleware.entity.transaction.RemittanceTransaction;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

public interface RemittanceTransactionService {
    List<RemittanceTransaction> findByTransactionStatusAndCompanyId(TransactionStatus status, int companyId);
    List<RemittanceTransaction> findByCompanyId(int userId);
    TransactionStatus getTransactionStatus(String status);
    List<String> processTransactions(@PathVariable int userId, @PathVariable String destCountry,@PathVariable String remittanceCompany, MultipartFile csvFile) throws Exception;
}
