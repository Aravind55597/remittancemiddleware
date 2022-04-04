package com.remittancemiddleware.remittancemiddleware.restcontroller;

import com.remittancemiddleware.remittancemiddleware.dataclass.custom.CustomResponse;
import org.springframework.web.bind.annotation.PathVariable;

public interface CompanyController {
    CustomResponse findAll();

    CustomResponse findById(@PathVariable int companyId);
}
