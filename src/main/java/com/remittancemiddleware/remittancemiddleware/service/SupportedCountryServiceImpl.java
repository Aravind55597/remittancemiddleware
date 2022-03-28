package com.remittancemiddleware.remittancemiddleware.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.remittancemiddleware.remittancemiddleware.dao.SupportedCountryDAO;
import com.remittancemiddleware.remittancemiddleware.entity.SupportedCountry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SupportedCountryServiceImpl implements SupportedCountryService {

    private SupportedCountryDAO supportedCountryDAO;

    @Autowired
    public SupportedCountryServiceImpl(SupportedCountryDAO theSupportedCountryDAO) {
        this.supportedCountryDAO = theSupportedCountryDAO;
    }

    @Override
    public List<SupportedCountry> findAll() {
        return supportedCountryDAO.findAll();
    };

}
