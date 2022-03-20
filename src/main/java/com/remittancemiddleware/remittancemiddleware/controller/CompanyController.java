package com.remittancemiddleware.remittancemiddleware.controller;

import com.remittancemiddleware.remittancemiddleware.entity.Company;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

public interface CompanyController {
    List<Company> findAll();
    Company findById(@PathVariable int companyId);
}
