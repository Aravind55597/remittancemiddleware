package com.remittancemiddleware.remittancemiddleware.restcontroller;

import com.remittancemiddleware.remittancemiddleware.dataclass.custom.CustomResponse;
import com.remittancemiddleware.remittancemiddleware.entity.Company;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface CompanyController {
    CustomResponse findAll();
    CustomResponse findById(@PathVariable int companyId);
}
