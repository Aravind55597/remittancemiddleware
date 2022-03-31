package com.remittancemiddleware.remittancemiddleware.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.remittancemiddleware.remittancemiddleware.dao.CompanyDAO;
import com.remittancemiddleware.remittancemiddleware.dao.RemittanceMapDAO;
import com.remittancemiddleware.remittancemiddleware.dao.SupportedCountryDAO;
import com.remittancemiddleware.remittancemiddleware.dao.UserDAO;
import com.remittancemiddleware.remittancemiddleware.entity.Company;
import com.remittancemiddleware.remittancemiddleware.entity.RemittanceCompany;
import com.remittancemiddleware.remittancemiddleware.entity.SupportedCountry;
import com.remittancemiddleware.remittancemiddleware.entity.User;
import com.remittancemiddleware.remittancemiddleware.entity.companyfieldmap.*;
import com.remittancemiddleware.remittancemiddleware.entity.remittanceapimap.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import java.util.*;


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
    public List<String> getRequiredFields(String destCountry) {
        SupportedCountry theCountry = supportedCountryDAO.findIdByibanName(destCountry);
        List<RemittanceCompany> remittanceCompanyList = theCountry.getRemittanceCompanies();
        Map<String, Boolean> map = new HashMap<>();
        List<String> result = new ArrayList<>();
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
                                            map = addToRequiredFieldsR(map, setAR);
                                        }
                                    }
                                }
                                else if (setR.getKey().equals("bankAccountMapApi")) {
                                    BankAccountMapApi bankAccountMapApi = receiverMapApi.getBankAccountMapApi();
                                    Map<String,String> bankAccountMap = oMapper.convertValue(bankAccountMapApi, Map.class);
                                    for (Map.Entry<String,String> setBR : bankAccountMap.entrySet()) {
                                        if (!(setBR.getKey().equals("id"))) {
                                            map = addToRequiredFieldsR(map, setBR);
                                        }
                                    }
                                }
                                else if (setR.getKey().equals("identificationMapApi")) {
                                    IdentificationMapApi identificationMapApi = receiverMapApi.getIdentificationMapApi();
                                    Map<String,String> identificationMap = oMapper.convertValue(identificationMapApi, Map.class);
                                    for (Map.Entry<String,String> setIR : identificationMap.entrySet()) {
                                        if (!(setIR.getKey().equals("id"))) {
                                            map = addToRequiredFieldsR(map, setIR);
                                        }
                                    }
                                }
                                else {
                                    map = addToRequiredFieldsR(map, setR);
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
                                            map = addToRequiredFieldsS(map, setAS);
                                        }
                                    }
                                }
                                else if (setS.getKey().equals("bankAccountMapApi")) {
                                    BankAccountMapApi bankAccountMapApi = senderMapApi.getBankAccountMapApi();
                                    Map<String,String> bankAccountMap = oMapper.convertValue(bankAccountMapApi, Map.class);
                                    for (Map.Entry<String,String> setBS : bankAccountMap.entrySet()) {
                                        if (!(setBS.getKey().equals("id"))) {
                                            map = addToRequiredFieldsS(map, setBS);
                                        }
                                    }
                                }
                                else if (setS.getKey().equals("identificationMapApi")) {
                                    IdentificationMapApi identificationMapApi = senderMapApi.getIdentificationMapApi();
                                    Map<String,String> identificationMap = oMapper.convertValue(identificationMapApi, Map.class);
                                    for (Map.Entry<String,String> setIS : identificationMap.entrySet()) {
                                        if (!(setIS.getKey().equals("id"))) {
                                            map = addToRequiredFieldsS(map, setIS);
                                        }
                                    }
                                }
                                else {
                                    map = addToRequiredFieldsS(map, setS);
                                }
                            }
                        }
                    }
                    else {
                        map = addToRequiredFieldsGen(map, set);
                    }
                }
            }
        }
        for (Map.Entry<String,Boolean> setMap: map.entrySet()) {
            if (setMap.getValue()) {
                result.add(setMap.getKey());
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

    @Override
    @Transactional
    public RemittanceMap save(int userId, String destCountry, Map<String,String> mappingDetails) {
        User theUser = userDAO.getById(userId);
        int companyId = theUser.getCompanyId();
        Company theCompany = companyDAO.getById(companyId);
        ObjectMapper oMapper = new ObjectMapper();

        RemittanceMap theRemittanceMap = new RemittanceMap();
        ReceiverMap theReceiverMap = new ReceiverMap();
        SenderMap theSenderMap = new SenderMap();
        AddressMap theAddressMapR = new AddressMap();
        AddressMap theAddressMapS = new AddressMap();
        BankAccountMap theBankAccountMapR = new BankAccountMap();
        BankAccountMap theBankAccountMapS = new BankAccountMap();
        IdentificationMap theIdentificationMapR = new IdentificationMap();
        IdentificationMap theIdentificationMapS = new IdentificationMap();

        Map<String,String> remittanceMap = oMapper.convertValue(theRemittanceMap, Map.class);
        Map<String,String> receiverMap = oMapper.convertValue(theReceiverMap, Map.class);
        Map<String,String> senderMap = oMapper.convertValue(theSenderMap, Map.class);
        Map<String,String> addressMapR = oMapper.convertValue(theAddressMapR, Map.class);
        Map<String,String> addressMapS = oMapper.convertValue(theAddressMapS, Map.class);
        Map<String,String> bankAccountMapR = oMapper.convertValue(theBankAccountMapR, Map.class);
        Map<String,String> bankAccountMapS = oMapper.convertValue(theBankAccountMapS, Map.class);
        Map<String,String> identificationMapR = oMapper.convertValue(theIdentificationMapR, Map.class);
        Map<String,String> identificationMapS = oMapper.convertValue(theIdentificationMapS, Map.class);

        remittanceMap.remove("id");
        receiverMap.remove("id");
        senderMap.remove("id");
        addressMapR.remove("id");
        addressMapS.remove("id");
        bankAccountMapR.remove("id");
        bankAccountMapS.remove("id");
        identificationMapR.remove("id");
        identificationMapS.remove("id");

        Set<String> keysRemittanceMap = remittanceMap.keySet();
        Set<String> keysReceiverMap = receiverMap.keySet();
        Set<String> keysSenderMap = senderMap.keySet();
        Set<String> keysAddressMapR = addressMapR.keySet();
        Set<String> keysBankAccMapR = bankAccountMapR.keySet();
        Set<String> keysIdentificationMapR = identificationMapR.keySet();
        Set<String> keysAddressMapS = addressMapS.keySet();
        Set<String> keysBankAccMapS = bankAccountMapS.keySet();
        Set<String> keysIdentificationMapS = identificationMapS.keySet();

        Set<String> keys = mappingDetails.keySet();
        for (String s:keys) {
            if (s.contains("receiver")) {
                addValuesToMap(mappingDetails, receiverMap, keysReceiverMap, s);
                addValuesToMap(mappingDetails, addressMapR, keysAddressMapR, s);
                addValuesToMap(mappingDetails, bankAccountMapR, keysBankAccMapR, s);
                addValuesToMap(mappingDetails, identificationMapR, keysIdentificationMapR, s);
            }
            else if (s.contains("sender")) {
                addValuesToMap(mappingDetails, senderMap, keysSenderMap, s);
                addValuesToMap(mappingDetails, addressMapS, keysAddressMapS, s);
                addValuesToMap(mappingDetails, bankAccountMapS, keysBankAccMapS, s);
                addValuesToMap(mappingDetails, identificationMapS, keysIdentificationMapS, s);
            }
            else {
                addValuesToMap(mappingDetails, remittanceMap, keysRemittanceMap, s);
            }
        }

        theRemittanceMap = oMapper.convertValue(remittanceMap, RemittanceMap.class);
        theReceiverMap = oMapper.convertValue(receiverMap, ReceiverMap.class);
        theSenderMap = oMapper.convertValue(senderMap, SenderMap.class);
        theAddressMapR = oMapper.convertValue(addressMapR, AddressMap.class);
        theAddressMapS = oMapper.convertValue(addressMapS, AddressMap.class);
        theBankAccountMapR = oMapper.convertValue(bankAccountMapR, BankAccountMap.class);
        theBankAccountMapS = oMapper.convertValue(bankAccountMapS, BankAccountMap.class);
        theIdentificationMapR = oMapper.convertValue(identificationMapR, IdentificationMap.class);
        theIdentificationMapS = oMapper.convertValue(identificationMapS, IdentificationMap.class);

        theReceiverMap.setAddressMap(theAddressMapR);
        theReceiverMap.setBankAccountMap(theBankAccountMapR);
        theReceiverMap.setIdentificationMap(theIdentificationMapR);

        theSenderMap.setAddressMap(theAddressMapS);
        theSenderMap.setBankAccountMap(theBankAccountMapS);
        theSenderMap.setIdentificationMap(theIdentificationMapS);

        theRemittanceMap.setReceiverMap(theReceiverMap);
        theRemittanceMap.setSenderMap(theSenderMap);

        theRemittanceMap.setCompany(theCompany);
        theRemittanceMap.setDestinationCountry(destCountry);
        remittanceMapDAO.save(theRemittanceMap);

        return theRemittanceMap;
    }

    private static void addValuesToMap(Map<String, String> mappingDetails, Map<String, String> SSOTMap, Set<String> keysSSOTMap, String s) {
        for (String s1: keysSSOTMap) {
            if (s.contains(s1)) {
                SSOTMap.put(s1, mappingDetails.get(s));
            }
        }
    }

}
