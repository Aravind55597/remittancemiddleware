package com.remittancemiddleware.remittancemiddleware.service;

import com.remittancemiddleware.remittancemiddleware.entity.Company;

import java.util.List;
import java.util.Map;

public interface CompanyService {
    public List<Company> findAll();
    public Company findById(int theId);
}
