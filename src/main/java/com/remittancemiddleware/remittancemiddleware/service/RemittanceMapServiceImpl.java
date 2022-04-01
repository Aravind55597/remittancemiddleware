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
    public Map<String,String> findMapByCountry(int userId, String destCountry) {
        User theUser = userDAO.getById(userId);
        int companyId = theUser.getCompanyId();
        Company theCompany = companyDAO.getById(companyId);
        Optional<RemittanceMap> result = Optional.ofNullable(remittanceMapDAO.findByCompanyAndDestinationCountry(theCompany, destCountry));

        RemittanceMap theRemittanceMap = null;
        Map<String, String> output = new HashMap<>();
        Map<String,String> processedOutput = new HashMap<>();

        if (result.isPresent()) {
            theRemittanceMap = result.get();
            ObjectMapper oMapper = new ObjectMapper();
            Map<String, String> remittanceMap = oMapper.convertValue(theRemittanceMap, Map.class);
            remittanceMap.remove("id");
            remittanceMap.remove("destinationCountry");
            for (Map.Entry<String, String> set : remittanceMap.entrySet()) {
                if (set.getKey().equals("receiverMap")) {
                    ReceiverMap theReceiverMap = theRemittanceMap.getReceiverMap();
                    Map<String,String> receiverMap = oMapper.convertValue(theReceiverMap, Map.class);
                    for (Map.Entry<String,String> setR : receiverMap.entrySet()) {
                        if (!(setR.getKey().equals("id"))) {
                            if (setR.getKey().equals("addressMap")) {
                                AddressMap theAddressMap = theReceiverMap.getAddressMap();
                                Map<String,String> addressMap = oMapper.convertValue(theAddressMap, Map.class);
                                for (Map.Entry<String,String> setAR : addressMap.entrySet()) {
                                    if (!(setAR.getKey().equals("id"))) {
                                        output = showRequiredFieldsR(output, setAR);
                                    }
                                }
                            }
                            else if (setR.getKey().equals("bankAccountMap")) {
                                BankAccountMap theBankAccountMap = theReceiverMap.getBankAccountMap();
                                Map<String,String> bankAccountMap = oMapper.convertValue(theBankAccountMap, Map.class);
                                for (Map.Entry<String,String> setBR : bankAccountMap.entrySet()) {
                                    if (!(setBR.getKey().equals("id"))) {
                                        output = showRequiredFieldsR(output, setBR);
                                    }
                                }
                            }
                            else if (setR.getKey().equals("identificationMap")) {
                                IdentificationMap theIdentificationMap = theReceiverMap.getIdentificationMap();
                                Map<String,String> identificationMap = oMapper.convertValue(theIdentificationMap, Map.class);
                                for (Map.Entry<String,String> setIR : identificationMap.entrySet()) {
                                    if (!(setIR.getKey().equals("id"))) {
                                        output = showRequiredFieldsR(output, setIR);
                                    }
                                }
                            }
                            else {
                                output = showRequiredFieldsR(output, setR);
                            }
                        }
                    }

                }
                else if(set.getKey().equals("senderMap")) {
                    SenderMap theSenderMap = theRemittanceMap.getSenderMap();
                    Map<String,String> senderMap = oMapper.convertValue(theSenderMap, Map.class);
                    for (Map.Entry<String,String> setS : senderMap.entrySet()) {
                        if (!(setS.getKey().equals("id"))) {
                            if (setS.getKey().equals("addressMap")) {
                                AddressMap theAddressMap = theSenderMap.getAddressMap();
                                Map<String,String> addressMap = oMapper.convertValue(theAddressMap, Map.class);
                                for (Map.Entry<String,String> setAS : addressMap.entrySet()) {
                                    if (!(setAS.getKey().equals("id"))) {
                                        output = showRequiredFieldsS(output, setAS);
                                    }
                                }
                            }
                            else if (setS.getKey().equals("bankAccountMap")) {
                                BankAccountMap theBankAccountMap = theSenderMap.getBankAccountMap();
                                Map<String,String> bankAccountMap = oMapper.convertValue(theBankAccountMap, Map.class);
                                for (Map.Entry<String,String> setBS : bankAccountMap.entrySet()) {
                                    if (!(setBS.getKey().equals("id"))) {
                                        output = showRequiredFieldsS(output, setBS);
                                    }
                                }
                            }
                            else if (setS.getKey().equals("identificationMap")) {
                                IdentificationMap theIdentificationMap = theSenderMap.getIdentificationMap();
                                Map<String,String> identificationMap = oMapper.convertValue(theIdentificationMap, Map.class);
                                for (Map.Entry<String,String> setIS : identificationMap.entrySet()) {
                                    if (!(setIS.getKey().equals("id"))) {
                                        output = showRequiredFieldsS(output, setIS);
                                    }
                                }
                            }
                            else {
                                output = showRequiredFieldsS(output, setS);
                            }
                        }
                    }
                }
                else {
                    output = showRequiredFieldsGen(output, set);
                }
            }
            for (Map.Entry<String,String> set0 : output.entrySet()) {
                if (set0.getValue() != null) {
                    processedOutput.put(set0.getKey(), set0.getValue());
                }
            }
        }

        return processedOutput;
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
            remittanceMap.remove("id");
            for (Map.Entry<String,String> set : remittanceMap.entrySet()) {
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
                    result.put("receiver "+set.getKey(), true);
                }
            }
        }
        else {
            if (set.getValue() == null) {
                result.put("receiver "+set.getKey(), false);
            } else {
                result.put("receiver "+set.getKey(), true);
            }
        }

        return result;
    }

    private static Map<String,Boolean> addToRequiredFieldsS(Map<String, Boolean> result, Map.Entry<String, String> set) {
        if (result.containsKey(set.getKey())) {
            if (!result.get(set.getKey())) {
                if (set.getValue() != null) {
                    result.put("sender "+set.getKey(), true);
                }
            }
        }
        else {
            if (set.getValue() == null) {
                result.put("sender "+set.getKey(), false);
            } else {
                result.put("sender "+set.getKey(), true);
            }
        }

        return result;
    }

    private static Map<String,String> showRequiredFieldsGen(Map<String, String> result, Map.Entry<String, String> set) {
        result.put(set.getKey(), set.getValue());

        return result;
    }

    private static Map<String,String> showRequiredFieldsR(Map<String, String> result, Map.Entry<String, String> set) {

        result.put("receiver "+set.getKey(), set.getValue());

        return result;
    }

    private static Map<String,String> showRequiredFieldsS(Map<String, String> result, Map.Entry<String, String> set) {

        result.put("sender "+set.getKey(), set.getValue());

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

    @Override
    @Transactional
    public RemittanceMap update(int userId, String destCountry, Map<String,String> mappingDetails) {
        User theUser = userDAO.getById(userId);
        int companyId = theUser.getCompanyId();
        Company theCompany = companyDAO.getById(companyId);
        ObjectMapper oMapper = new ObjectMapper();

        Optional<RemittanceMap> result = Optional.ofNullable(remittanceMapDAO.findByCompanyAndDestinationCountry(theCompany, destCountry));

        RemittanceMap theRemittanceMap = null;
        Map<String, String> output = new HashMap<>();

        if (result.isPresent()) {
            theRemittanceMap = result.get();
            int id = theRemittanceMap.getId();

            ReceiverMap theReceiverMap = theRemittanceMap.getReceiverMap();
            int rId = theReceiverMap.getId();

            SenderMap theSenderMap = theRemittanceMap.getSenderMap();
            int sId = theSenderMap.getId();

            AddressMap theAddressMapR = theReceiverMap.getAddressMap();
            int arId = theAddressMapR.getId();

            AddressMap theAddressMapS = theSenderMap.getAddressMap();
            int asId = theAddressMapS.getId();

            BankAccountMap theBankAccountMapR = theReceiverMap.getBankAccountMap();
            int brId = theBankAccountMapR.getId();

            BankAccountMap theBankAccountMapS = theSenderMap.getBankAccountMap();
            int bsId = theBankAccountMapS.getId();

            IdentificationMap theIdentificationMapR = theReceiverMap.getIdentificationMap();
            int irId = theIdentificationMapR.getId();

            IdentificationMap theIdentificationMapS = theSenderMap.getIdentificationMap();
            int isId = theIdentificationMapS.getId();

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

            theAddressMapR.setId(arId);
            theBankAccountMapR.setId(brId);
            theIdentificationMapR.setId(irId);

            theReceiverMap.setAddressMap(theAddressMapR);
            theReceiverMap.setBankAccountMap(theBankAccountMapR);
            theReceiverMap.setIdentificationMap(theIdentificationMapR);

            theAddressMapS.setId(asId);
            theBankAccountMapS.setId(bsId);
            theIdentificationMapS.setId(isId);
            
            theSenderMap.setAddressMap(theAddressMapS);
            theSenderMap.setBankAccountMap(theBankAccountMapS);
            theSenderMap.setIdentificationMap(theIdentificationMapS);

            theReceiverMap.setId(rId);
            theSenderMap.setId(sId);

            theRemittanceMap.setReceiverMap(theReceiverMap);
            theRemittanceMap.setSenderMap(theSenderMap);

            theRemittanceMap.setCompany(theCompany);
            theRemittanceMap.setDestinationCountry(destCountry);
            theRemittanceMap.setId(id);

            remittanceMapDAO.save(theRemittanceMap);
        }

        else {
            throw new CustomNotFoundException("There is no existing remittance map for " + destCountry);
        }


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
