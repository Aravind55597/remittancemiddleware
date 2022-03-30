package com.remittancemiddleware.remittancemiddleware.service;

import com.remittancemiddleware.remittancemiddleware.entity.RemittanceCompany;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RemittanceCompanyService {
    @Transactional
    List<RemittanceCompany> findRemittanceCompanyById(int cId);
}
