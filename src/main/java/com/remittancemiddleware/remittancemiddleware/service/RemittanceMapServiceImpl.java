package com.remittancemiddleware.remittancemiddleware.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.remittancemiddleware.remittancemiddleware.customexception.CustomNotFoundException;
import com.remittancemiddleware.remittancemiddleware.dao.CompanyDAO;
import com.remittancemiddleware.remittancemiddleware.dao.RemittanceMapDAO;
import com.remittancemiddleware.remittancemiddleware.dao.SupportedCountryDAO;
import com.remittancemiddleware.remittancemiddleware.dao.UserDAO;
import com.remittancemiddleware.remittancemiddleware.entity.Company;
import com.remittancemiddleware.remittancemiddleware.entity.RemittanceCompany;
import com.remittancemiddleware.remittancemiddleware.entity.SupportedCountry;
import com.remittancemiddleware.remittancemiddleware.entity.User;
import com.remittancemiddleware.remittancemiddleware.entity.companyfieldmap.RemittanceMap;
import com.remittancemiddleware.remittancemiddleware.entity.remittanceapimap.*;
import com.remittancemiddleware.remittancemiddleware.entity.transaction.Identification;
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
    private UserDAO userDAO;

    @Autowired
    public RemittanceMapServiceImpl(RemittanceMapDAO theRemittanceMapDAO, SupportedCountryDAO theSupportedCountryDAO, CompanyDAO theCompanyDAO, UserDAO theUserDAO) {
        this.remittanceMapDAO = theRemittanceMapDAO;
        this.supportedCountryDAO = theSupportedCountryDAO;
        this.companyDAO = theCompanyDAO;
        this.userDAO = theUserDAO;
    }

    @Override
    public RemittanceMap findMapByCountry(int userId, String destCountry) {
        User theUser = userDAO.getById(userId);
        int companyId = theUser.getCompanyId();
        Company theCompany = companyDAO.getById(companyId);
        Optional<RemittanceMap> result = Optional.ofNullable(remittanceMapDAO.findByCompanyAndDestinationCountry(theCompany, destCountry));

        RemittanceMap theRemittanceMap = null;

        if (result.isPresent()) {
            theRemittanceMap = result.get();
        }
        return theRemittanceMap;
    }

    @Override
    @Transactional
    public Map<String,Boolean> getRequiredFields(String destCountry) {
        SupportedCountry theCountry = supportedCountryDAO.findIdByibanName(destCountry);
        List<RemittanceCompany> remittanceCompanyList = theCountry.getRemittanceCompanies();
        Map<String, Boolean> result = new HashMap<>();
        for (RemittanceCompany aCompany:remittanceCompanyList) {
            RemittanceMapApi remittanceMapApi = aCompany.getRemittanceMapApi();
            ObjectMapper oMapper = new ObjectMapper();
            Map<String,String> remittanceMap = oMapper.convertValue(remittanceMapApi, Map.class);
            for (Map.Entry<String,String> set : remittanceMap.entrySet()) {
                if (!(set.getKey().equals("id"))) {
                    if (set.getKey().equals("receiverMapApi")) {
                        ReceiverMapApi receiverMapApi = remittanceMapApi.getReceiverMapApi();
                        Map<String,String> receiverMap = oMapper.convertValue(receiverMapApi, Map.class);
                        for (Map.Entry<String,String> setR : receiverMap.entrySet()) {
                            if (!(setR.getKey().equals("id"))) {
                                if (setR.getKey().equals("addressMapApi")) {
                                    AddressMapApi addressMapApi = receiverMapApi.getAddressMapApi();
                                    Map<String,String> addressMap = oMapper.convertValue(addressMapApi, Map.class);
                                    for (Map.Entry<String,String> setAR : addressMap.entrySet()) {
                                        if (!(setAR.getKey().equals("id"))) {
                                            result = addToRequiredFieldsR(result, setAR);
                                        }
                                    }
                                }
                                else if (setR.getKey().equals("bankAccountMapApi")) {
                                    BankAccountMapApi bankAccountMapApi = receiverMapApi.getBankAccountMapApi();
                                    Map<String,String> bankAccountMap = oMapper.convertValue(bankAccountMapApi, Map.class);
                                    for (Map.Entry<String,String> setBR : bankAccountMap.entrySet()) {
                                        if (!(setBR.getKey().equals("id"))) {
                                            result = addToRequiredFieldsR(result, setBR);
                                        }
                                    }
                                }
                                else if (setR.getKey().equals("identificationMapApi")) {
                                    IdentificationMapApi identificationMapApi = receiverMapApi.getIdentificationMapApi();
                                    Map<String,String> identificationMap = oMapper.convertValue(identificationMapApi, Map.class);
                                    for (Map.Entry<String,String> setIR : identificationMap.entrySet()) {
                                        if (!(setIR.getKey().equals("id"))) {
                                            result = addToRequiredFieldsR(result, setIR);
                                        }
                                    }
                                }
                                else {
                                    result = addToRequiredFieldsR(result, setR);
                                }
                            }
                        }

                    }
                    else if(set.getKey().equals("senderMapApi")) {
                        SenderMapApi senderMapApi = remittanceMapApi.getSenderMapApi();
                        Map<String,String> senderMap = oMapper.convertValue(senderMapApi, Map.class);
                        for (Map.Entry<String,String> setS : senderMap.entrySet()) {
                            if (!(setS.getKey().equals("id"))) {
                                if (setS.getKey().equals("addressMapApi")) {
                                    AddressMapApi addressMapApi = senderMapApi.getAddressMapApi();
                                    Map<String,String> addressMap = oMapper.convertValue(addressMapApi, Map.class);
                                    for (Map.Entry<String,String> setAS : addressMap.entrySet()) {
                                        if (!(setAS.getKey().equals("id"))) {
                                            result = addToRequiredFieldsS(result, setAS);
                                        }
                                    }
                                }
                                else if (setS.getKey().equals("bankAccountMapApi")) {
                                    BankAccountMapApi bankAccountMapApi = senderMapApi.getBankAccountMapApi();
                                    Map<String,String> bankAccountMap = oMapper.convertValue(bankAccountMapApi, Map.class);
                                    for (Map.Entry<String,String> setBS : bankAccountMap.entrySet()) {
                                        if (!(setBS.getKey().equals("id"))) {
                                            result = addToRequiredFieldsS(result, setBS);
                                        }
                                    }
                                }
                                else if (setS.getKey().equals("identificationMapApi")) {
                                    IdentificationMapApi identificationMapApi = senderMapApi.getIdentificationMapApi();
                                    Map<String,String> identificationMap = oMapper.convertValue(identificationMapApi, Map.class);
                                    for (Map.Entry<String,String> setIS : identificationMap.entrySet()) {
                                        if (!(setIS.getKey().equals("id"))) {
                                            result = addToRequiredFieldsS(result, setIS);
                                        }
                                    }
                                }
                                else {
                                    result = addToRequiredFieldsS(result, setS);
                                }
                            }
                        }
                    }
                    else {
                        result = addToRequiredFieldsGen(result, set);
                    }
                }
            }
        }
        return result;
    }

    private static Map<String,Boolean> addToRequiredFieldsGen(Map<String, Boolean> result, Map.Entry<String, String> set) {
        if (result.containsKey(set.getKey())) {
            if (!result.get(set.getKey())) {
                if (set.getValue() != null) {
                    result.put(set.getKey(), true);
                }
            }
        }
        else {
            if (set.getValue() == null) {
                result.put(set.getKey(), false);
            } else {
                result.put(set.getKey(), true);
            }
        }

        return result;
    }

    private static Map<String,Boolean> addToRequiredFieldsR(Map<String, Boolean> result, Map.Entry<String, String> set) {
        if (result.containsKey(set.getKey())) {
            if (!result.get(set.getKey())) {
                if (set.getValue() != null) {
                    result.put("receiver"+set.getKey(), true);
                }
            }
        }
        else {
            if (set.getValue() == null) {
                result.put("receiver"+set.getKey(), false);
            } else {
                result.put("receiver"+set.getKey(), true);
            }
        }

        return result;
    }

    private static Map<String,Boolean> addToRequiredFieldsS(Map<String, Boolean> result, Map.Entry<String, String> set) {
        if (result.containsKey(set.getKey())) {
            if (!result.get(set.getKey())) {
                if (set.getValue() != null) {
                    result.put("sender"+set.getKey(), true);
                }
            }
        }
        else {
            if (set.getValue() == null) {
                result.put("sender"+set.getKey(), false);
            } else {
                result.put("sender"+set.getKey(), true);
            }
        }

        return result;
    }

//    @Override
//    @Transactional
//    public RemittanceMap save(Map<String,String> mappingDetails) {
//
//    }
}
