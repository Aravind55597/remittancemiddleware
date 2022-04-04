package com.remittancemiddleware.remittancemiddleware.dao;

import com.remittancemiddleware.remittancemiddleware.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyDAO extends JpaRepository<Company, Integer> {
    List<Company> findByCompanyName(String companyName);
}
