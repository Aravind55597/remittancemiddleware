package com.remittancemiddleware.remittancemiddleware.controller;

import com.remittancemiddleware.remittancemiddleware.entity.transaction.RemittanceTransaction;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface RemittanceTransactionController {
    List<RemittanceTransaction> getTransactionsByStatus(@RequestParam int userId);
    List<RemittanceTransaction> getTransactionsByStatus(@RequestParam String status, @RequestParam int userId);
}
