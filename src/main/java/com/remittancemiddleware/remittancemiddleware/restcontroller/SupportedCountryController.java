package com.remittancemiddleware.remittancemiddleware.restcontroller;

import com.remittancemiddleware.remittancemiddleware.dao.SupportedCountryDAO;
import com.remittancemiddleware.remittancemiddleware.entity.RemittanceCompany;
import com.remittancemiddleware.remittancemiddleware.entity.SupportedCountry;
import com.remittancemiddleware.remittancemiddleware.entity.transaction.RemittanceTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/remittanceTransaction")
public class SupportedCountryController {
    @Autowired
    private SupportedCountryDAO supportedCountryDAO;

    @GetMapping("/getCountryByUser")
    public SupportedCountry getCountryByUser(@RequestParam int countryId){
        SupportedCountry supportedCountry = supportedCountryDAO.findById(countryId).get();
        return supportedCountry;
    }

    @GetMapping("/getRemittanceCompany")
    @Transactional
    public List<RemittanceCompany> getRemittanceCompany(@RequestParam int countryId){
        List<RemittanceCompany> remittanceCompany = supportedCountryDAO.findById(countryId).get().getRemittanceCompanies();

        return remittanceCompany;
    }

}
