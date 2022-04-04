package com.remittancemiddleware.remittancemiddleware.service;

import com.remittancemiddleware.remittancemiddleware.entity.Company;

import java.util.List;

public interface CompanyService {
    List<Company> findAll();

    Company findById(int theId);
}
