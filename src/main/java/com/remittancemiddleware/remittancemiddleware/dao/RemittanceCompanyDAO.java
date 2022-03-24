package com.remittancemiddleware.remittancemiddleware.dao;

import com.remittancemiddleware.remittancemiddleware.entity.RemittanceCompany;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RemittanceCompanyDAO extends JpaRepository<RemittanceCompany, Integer> {
}