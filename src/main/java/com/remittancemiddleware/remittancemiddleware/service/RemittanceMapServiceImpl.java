package com.remittancemiddleware.remittancemiddleware.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.remittancemiddleware.remittancemiddleware.customexception.CustomNotFoundException;
import com.remittancemiddleware.remittancemiddleware.dao.CompanyDAO;
import com.remittancemiddleware.remittancemiddleware.dao.RemittanceMapDAO;
import com.remittancemiddleware.remittancemiddleware.dao.SupportedCountryDAO;
import com.remittancemiddleware.remittancemiddleware.entity.Company;
import com.remittancemiddleware.remittancemiddleware.entity.RemittanceCompany;
import com.remittancemiddleware.remittancemiddleware.entity.SupportedCountry;
import com.remittancemiddleware.remittancemiddleware.entity.companyfieldmap.RemittanceMap;
import com.remittancemiddleware.remittancemiddleware.entity.remittanceapimap.RemittanceMapApi;
import org.apache.commons.collections.map.CompositeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class RemittanceMapServiceImpl implements RemittanceMapService {
    private RemittanceMapDAO remittanceMapDAO;
    private SupportedCountryDAO supportedCountryDAO;
    private CompanyDAO companyDAO;

    @Autowired
    public RemittanceMapServiceImpl(RemittanceMapDAO theRemittanceMapDAO, SupportedCountryDAO theSupportedCountryDAO, CompanyDAO theCompanyDAO) {
        this.remittanceMapDAO = theRemittanceMapDAO;
        this.supportedCountryDAO = theSupportedCountryDAO;
        this.companyDAO = theCompanyDAO;
    }

    @Override
    public RemittanceMap findMapByCountry(int companyId, String destCountry) {
        Company theCompany = companyDAO.getById(companyId);
        Optional<RemittanceMap> result = Optional.ofNullable(remittanceMapDAO.findByCompanyAndDestinationCountry(theCompany, destCountry));

        RemittanceMap theRemittanceMap = null;

        if (result.isPresent()) {
            theRemittanceMap = result.get();
        }
        return theRemittanceMap;
    }

//    @Override
//    @Transactional
//    public Map<String,Boolean> getRequiredFields(String destCountry) {
//        SupportedCountry theCountry = supportedCountryDAO.findIdByibanName(destCountry);
//        List<RemittanceCompany> remittanceCompanyList = theCountry.getRemittanceCompanies();
//        Map<String, Boolean> result = new HashMap<>();
//        for (RemittanceCompany aCompany:remittanceCompanyList) {
//            RemittanceMapApi remittanceMapApi = aCompany.getRemittanceMapApi();
//            ObjectMapper oMapper = new ObjectMapper();
////            if (result.containsKey("Amount")) {
////                if (result.get("Amount") == false) {
////                    if (remittanceMapApi.getAmount() != null) {
////                        result.put("Amount", true);
////                    }
////                }
////            }
////            else {
////                if (remittanceMapApi.getAmount() == null) {
////                    result.put("Amount", false);
////                } else {
////                    result.put("Amount", true);
////                }
////            }
//        }
//        return result;
//    }

//    @Override
//    @Transactional
//    public RemittanceMap save(Map<String,String> mappingDetails) {
//
//    }
}
