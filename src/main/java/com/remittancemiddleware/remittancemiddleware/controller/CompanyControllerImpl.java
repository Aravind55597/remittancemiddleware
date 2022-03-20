package com.remittancemiddleware.remittancemiddleware.controller;

import com.remittancemiddleware.remittancemiddleware.entity.Company;

import com.remittancemiddleware.remittancemiddleware.service.CompanyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CompanyControllerImpl implements CompanyController {
    private final CompanyServiceImpl companyServiceImpl;

    @Autowired
    private CompanyControllerImpl(CompanyServiceImpl theCompanyServiceImpl) {
        this.companyServiceImpl = theCompanyServiceImpl;
    }
    @GetMapping("/company")
    public List<Company> findAll() {
        return companyServiceImpl.findAll();
    };

    @GetMapping("/company/{companyId}")
    public Company findById(@PathVariable int companyId) {
        return companyServiceImpl.findById(companyId);
    };

}
