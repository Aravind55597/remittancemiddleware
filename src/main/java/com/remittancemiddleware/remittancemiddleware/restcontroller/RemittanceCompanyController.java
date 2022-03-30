package com.remittancemiddleware.remittancemiddleware.restcontroller;

import com.remittancemiddleware.remittancemiddleware.dataclass.custom.CustomResponse;
import org.springframework.web.bind.annotation.RequestParam;

public interface RemittanceCompanyController {
    CustomResponse findRemittanceCompanyById(@RequestParam int cId);
}
