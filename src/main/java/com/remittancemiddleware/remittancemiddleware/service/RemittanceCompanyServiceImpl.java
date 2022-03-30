package com.remittancemiddleware.remittancemiddleware.service;

import com.remittancemiddleware.remittancemiddleware.dao.RemittanceCompanyDAO;
import com.remittancemiddleware.remittancemiddleware.entity.RemittanceCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RemittanceCompanyServiceImpl implements RemittanceCompanyService{

    private RemittanceCompanyDAO remittanceCompanyDAO;

    @Autowired
    public RemittanceCompanyServiceImpl(RemittanceCompanyDAO theRemittanceCompanyDAO) {
        this.remittanceCompanyDAO = theRemittanceCompanyDAO;
    }

    @Override
    public List<RemittanceCompany> findAll() {
        return remittanceCompanyDAO.findAll();
    };

}
