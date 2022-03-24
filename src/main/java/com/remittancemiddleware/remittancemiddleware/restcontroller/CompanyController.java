package com.remittancemiddleware.remittancemiddleware.restcontroller;

import com.remittancemiddleware.remittancemiddleware.entity.Company;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface CompanyController {
    List<Company> findAll();
    Company findById(@PathVariable int companyId);
}
