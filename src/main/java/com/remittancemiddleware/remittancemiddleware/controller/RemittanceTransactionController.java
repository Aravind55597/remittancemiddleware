package com.remittancemiddleware.remittancemiddleware.controller;

import com.remittancemiddleware.remittancemiddleware.entity.User;
import com.remittancemiddleware.remittancemiddleware.entity.transaction.RemittanceTransaction;
import com.remittancemiddleware.remittancemiddleware.enums.TransactionStatus;
import com.remittancemiddleware.remittancemiddleware.service.RemittanceTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/remittanceTransaction")
public class RemittanceTransactionController {
    private RemittanceTransactionService remittanceTransactionService;

    @Autowired
    public RemittanceTransactionController(RemittanceTransactionService theRemittanceTransactionService){
        this.remittanceTransactionService = theRemittanceTransactionService;
    }


    @GetMapping("/getTransactionsByUser")
    public List<RemittanceTransaction> getTransactionsByStatus(@RequestParam int userId){
        List<RemittanceTransaction> remittanceTransaction = remittanceTransactionService.findByCompanyId(userId);
        return remittanceTransaction;
    }

    @GetMapping("/getTransactionsByStatus")
    public List<RemittanceTransaction> getTransactionsByStatus(@RequestParam String status, @RequestParam int userId){
        TransactionStatus transactionStatus = null;
        if (status.equals("SUCCESSFUL")){
            transactionStatus = TransactionStatus.SUCCESSFUL;
        } else if (status.equals("PENDING_AML")){
            transactionStatus = TransactionStatus.PENDING_AML;
        } else if (status.equals("PENDING_COMPLIANCE_CHECKS")){
            transactionStatus = TransactionStatus.PENDING_COMPLIANCE_CHECKS;
        } else if (status.equals("REJECTED")){
            transactionStatus = TransactionStatus.REJECTED;
        }

        List<RemittanceTransaction> remittanceTransaction = remittanceTransactionService.findByTransactionStatusAndCompanyId(transactionStatus, userId);
        return remittanceTransaction;
    }
}
