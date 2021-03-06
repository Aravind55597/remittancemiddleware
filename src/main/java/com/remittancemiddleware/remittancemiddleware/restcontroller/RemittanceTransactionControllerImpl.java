package com.remittancemiddleware.remittancemiddleware.restcontroller;

import com.remittancemiddleware.remittancemiddleware.dataclass.custom.CustomResponse;
import com.remittancemiddleware.remittancemiddleware.entity.enumdata.TransactionStatus;
import com.remittancemiddleware.remittancemiddleware.entity.transaction.RemittanceTransaction;
import com.remittancemiddleware.remittancemiddleware.service.RemittanceTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/remittanceTransaction")
public class RemittanceTransactionControllerImpl implements RemittanceTransactionController {
    private final RemittanceTransactionService remittanceTransactionService;

    @Autowired
    public RemittanceTransactionControllerImpl(RemittanceTransactionService theRemittanceTransactionService) {
        this.remittanceTransactionService = theRemittanceTransactionService;
    }


    @GetMapping("/getTransactionsByUser")
    public CustomResponse getTransactionsByUser(@RequestParam int userId) {
        List<RemittanceTransaction> remittanceTransaction = remittanceTransactionService.findByCompanyId(userId);
        CustomResponse result = new CustomResponse(remittanceTransaction);
        return result;
    }

    @GetMapping("/getTransactionsByStatus")
    public CustomResponse getTransactionsByStatus(@RequestParam String status, @RequestParam int userId) {
        TransactionStatus transactionStatus = remittanceTransactionService.getTransactionStatus(status);

        List<RemittanceTransaction> remittanceTransaction = remittanceTransactionService.findByTransactionStatusAndCompanyId(transactionStatus, userId);
        CustomResponse result = new CustomResponse(remittanceTransaction);
        return result;
    }

    @PostMapping("/processTransactions/{userId}/{destCountry}/{remittanceCompany}")
    public CustomResponse processTransactions(@PathVariable int userId, @PathVariable String destCountry, @PathVariable String remittanceCompany, @RequestParam("csvFile") MultipartFile csvFile) throws Exception {

        try {
            List<String> response = remittanceTransactionService.processTransactions(userId, destCountry, remittanceCompany, csvFile);
            CustomResponse result = new CustomResponse(response);
            return result;
        } catch (Exception e) {
            throw e;
        }

    }

}
