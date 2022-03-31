package com.remittancemiddleware.remittancemiddleware.service;

import com.remittancemiddleware.remittancemiddleware.dao.RemittanceCompanyDAO;
import com.remittancemiddleware.remittancemiddleware.dao.SupportedCountryDAO;
import com.remittancemiddleware.remittancemiddleware.entity.RemittanceCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RemittanceCompanyServiceImpl implements RemittanceCompanyService{

    private RemittanceCompanyDAO remittanceCompanyDAO;
    private SupportedCountryDAO supportedCountryDAO;

    @Autowired
    public RemittanceCompanyServiceImpl(RemittanceCompanyDAO theRemittanceCompanyDAO, SupportedCountryDAO theSupportedCountryDAO) {
        this.remittanceCompanyDAO = theRemittanceCompanyDAO;
        this.supportedCountryDAO = theSupportedCountryDAO;
    }

    @Override
    @Transactional
    public List<RemittanceCompany> findRemittanceCompanyById(int cId) {
        List<RemittanceCompany> rc = supportedCountryDAO.findById(cId).get().getRemittanceCompanies();
        return rc;
    }

}
