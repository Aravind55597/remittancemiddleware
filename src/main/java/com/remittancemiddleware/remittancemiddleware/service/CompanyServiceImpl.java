package com.remittancemiddleware.remittancemiddleware.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.remittancemiddleware.remittancemiddleware.dao.CompanyDAO;
import com.remittancemiddleware.remittancemiddleware.entity.Company;
import com.remittancemiddleware.remittancemiddleware.customexception.CustomNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private CompanyDAO companyDAO;

    @Autowired
    public CompanyServiceImpl(CompanyDAO theCompanyDAO) {
        this.companyDAO = theCompanyDAO;
    }

    @Override
    public List<Company> findAll() {
        return companyDAO.findAll();
    };

    @Override
    public Company findById(int companyId) {
        Optional<Company> result = companyDAO.findById(companyId);
        Company theCompany = null;

        if (result.isPresent()) {
            theCompany = result.get();
        } else {
            throw new CustomNotFoundException("Did not find company id - " + companyId);
        }

        return theCompany;
    };

}
