package com.remittancemiddleware.remittancemiddleware.restcontroller;

import com.remittancemiddleware.remittancemiddleware.dataclass.custom.CustomResponse;
import com.remittancemiddleware.remittancemiddleware.entity.transaction.RemittanceTransaction;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RemittanceTransactionController {
    CustomResponse getTransactionsByUser(@RequestParam int userId);
    CustomResponse getTransactionsByStatus(@RequestParam String status, @RequestParam int userId);
    CustomResponse processTransactions(@PathVariable int userId, @PathVariable String destCountry,@PathVariable String remittanceCompany, MultipartFile csvFile) throws Exception;
}
