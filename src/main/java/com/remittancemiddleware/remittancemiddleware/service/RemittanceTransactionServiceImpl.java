package com.remittancemiddleware.remittancemiddleware.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pemistahl.lingua.api.Language;
import com.github.pemistahl.lingua.api.LanguageDetector;
import com.remittancemiddleware.remittancemiddleware.customexception.CustomBadRequestException;
import com.remittancemiddleware.remittancemiddleware.customexception.CustomNotFoundException;
import com.remittancemiddleware.remittancemiddleware.dao.CompanyDAO;
import com.remittancemiddleware.remittancemiddleware.dao.RemittanceMapDAO;
import com.remittancemiddleware.remittancemiddleware.dao.RemittanceTransactionDAO;
import com.remittancemiddleware.remittancemiddleware.dao.UserDAO;
import com.remittancemiddleware.remittancemiddleware.dataclass.remittance.everywhereremit.EverywhereRemitData;
import com.remittancemiddleware.remittancemiddleware.dataclass.remittance.financenow.FinanceNowData;
import com.remittancemiddleware.remittancemiddleware.dataclass.remittance.paymentgo.PaymentGoData;
import com.remittancemiddleware.remittancemiddleware.dataclass.sandbox.SandboxResponse;
import com.remittancemiddleware.remittancemiddleware.entity.Company;
import com.remittancemiddleware.remittancemiddleware.entity.User;
import com.remittancemiddleware.remittancemiddleware.entity.companyfieldmap.*;
import com.remittancemiddleware.remittancemiddleware.entity.enumdata.RemittanceCompanyName;
import com.remittancemiddleware.remittancemiddleware.entity.enumdata.TransactionStatus;
import com.remittancemiddleware.remittancemiddleware.entity.transaction.*;
import com.remittancemiddleware.remittancemiddleware.service.mapper.SSOTToEverywhereRemitMapper;
import com.remittancemiddleware.remittancemiddleware.service.mapper.SSOTToFinanceNowMapper;
import com.remittancemiddleware.remittancemiddleware.service.mapper.SSOTToPaymentGoMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class RemittanceTransactionServiceImpl implements RemittanceTransactionService {
    private final RemittanceTransactionDAO remittanceTransactionDAO;
    private final UserDAO userDAO;
    private final CompanyDAO companyDAO;
    private final RemittanceMapDAO remittanceMapDAO;
    private final CsvProcessorService csvProcessorService;
    private final SSOTToEverywhereRemitMapper ssotToEverywhereRemitMapper;
    private final SSOTToFinanceNowMapper ssotToFinanceNowMapper;
    private final SSOTToPaymentGoMapper ssotToPaymentGoMapper;
    private final SandboxAPIService sandboxAPIService;
    private final LanguageDetector languageDetector;
    private final ObjectMapper oMapper;

    public RemittanceTransactionServiceImpl(RemittanceTransactionDAO theRemittanceTransactionDAO, UserDAO theUserDAO,
                                            CompanyDAO theCompanyDAO, RemittanceMapDAO theRemittanceMapDAO,
                                            CsvProcessorService csvProcessorService,
                                            SSOTToEverywhereRemitMapper ssotToEverywhereRemitMapper,
                                            SSOTToFinanceNowMapper ssotToFinanceNowMapper,
                                            SSOTToPaymentGoMapper ssotToPaymentGoMapper,
                                            SandboxAPIService sandboxAPIService,
                                            LanguageDetector languageDetector,
                                            ObjectMapper oMapper) {
        this.remittanceTransactionDAO = theRemittanceTransactionDAO;
        this.userDAO = theUserDAO;
        this.companyDAO = theCompanyDAO;
        this.remittanceMapDAO = theRemittanceMapDAO;
        this.csvProcessorService = csvProcessorService;
        this.ssotToEverywhereRemitMapper = ssotToEverywhereRemitMapper;
        this.ssotToFinanceNowMapper = ssotToFinanceNowMapper;
        this.ssotToPaymentGoMapper = ssotToPaymentGoMapper;
        this.sandboxAPIService = sandboxAPIService;
        this.languageDetector=languageDetector;
        this.oMapper=oMapper;
    }

    @Override
    public List<RemittanceTransaction> findByTransactionStatusAndCompanyId(TransactionStatus status, int userId) throws CustomNotFoundException {

        Optional<User> result = userDAO.findById(userId);

        User theUser = null;

        if (result.isPresent()) {
            theUser = result.get();
        } else {
            // we didn't find the user
            throw new CustomNotFoundException("Did not find user id - " + userId);
        }
        Company company = theUser.getCompany();
        int companyId = company.getId();

        return remittanceTransactionDAO.findByTransactionStatusAndCompanyId(status, companyId);
    }

    @Override
    public List<RemittanceTransaction> findByCompanyId(int userId) throws CustomNotFoundException {
        Optional<User> result = userDAO.findById(userId);

        User theUser = null;

        if (result.isPresent()) {
            theUser = result.get();
        } else {
            // we didn't find the user
            throw new CustomNotFoundException("Did not find user id - " + userId);
        }
        Company company = theUser.getCompany();
        int companyId = company.getId();

        return remittanceTransactionDAO.findByCompanyId(companyId);
    }

    @Override
    public TransactionStatus getTransactionStatus(String status) throws CustomNotFoundException {
        TransactionStatus transactionStatus = null;
        if (status.equals("SUCCESSFUL")) {
            transactionStatus = TransactionStatus.SUCCESSFUL;
        } else if (status.equals("PENDING_AML")) {
            transactionStatus = TransactionStatus.PENDING_AML;
        } else if (status.equals("PENDING_COMPLIANCE_CHECKS")) {
            transactionStatus = TransactionStatus.PENDING_COMPLIANCE_CHECKS;
        } else if (status.equals("REJECTED")) {
            transactionStatus = TransactionStatus.REJECTED;
        } else {
            throw new CustomNotFoundException("Incorrect status input - " + status);
        }

        return transactionStatus;
    }

    @Override
    public List<String> processTransactions(@PathVariable int userId, @PathVariable String destCountry,
                                            @PathVariable String remittanceCompany, MultipartFile csvFile) throws Exception {
        User theUser = userDAO.getById(userId);
        int companyId = theUser.getCompanyId();
        Company theCompany = companyDAO.getById(companyId);
        ArrayList<String> output = new ArrayList<>();

        Optional<RemittanceMap> result = Optional.ofNullable(remittanceMapDAO.findByCompanyAndDestinationCountry(theCompany, destCountry));

        RemittanceMap theRemittanceMap = null;


        if (result.isPresent()) {
            theRemittanceMap = result.get();
            List<Map<String, String>> transactions = csvProcessorService.processCsv(csvFile);
//            ObjectMapper oMapper = new ObjectMapper();
            Map<String, String> remittanceMap = oMapper.convertValue(theRemittanceMap, Map.class);

            for (Map<String, String> t : transactions) {
                Map<String, String> transaction = new HashMap<>();
                Map<String, String> receiver = new HashMap<>();
                Map<String, String> sender = new HashMap<>();
                Map<String, String> addressR = new HashMap<>();
                Map<String, String> addressS = new HashMap<>();
                Map<String, String> bankAccountR = new HashMap<>();
                Map<String, String> bankAccountS = new HashMap<>();
                Map<String, String> identificationR = new HashMap<>();
                Map<String, String> identificationS = new HashMap<>();
                int counter = 1;

                for (Map.Entry<String, String> set : t.entrySet()) {
                    for (Map.Entry<String, String> setRM : remittanceMap.entrySet()) {
                        if (!(setRM.getKey().equals("id"))) {
                            if (setRM.getKey().equals("receiverMap")) {

                                mapReceiver(theRemittanceMap, oMapper, receiver, addressR, bankAccountR, identificationR, set, counter, output);
                            } else if (setRM.getKey().equals("senderMap")) {
                                mapSender(theRemittanceMap, oMapper, sender, addressS, bankAccountS, identificationS, set, counter, output);

                                // set -> csv hashmap (key is column name , value is value)
                                // setRM -> remittance map hashmap (key is ssot field name , value is column name)
                            } else if (set.getKey().equals(setRM.getValue())) {

                                //TODO
                                //if setRM.getKey() is amount
                                // check if double string or integer string
                                try{
                                    if(setRM.getKey()=="amount"){
                                        Double.parseDouble(set.getValue());

                                    }
                                }
                                catch(NumberFormatException ex){
                                    output.add("Transaction " + counter + ": error due to " + setRM.getValue() + " is not a formatted number");
                                }

                                transaction.put(setRM.getKey(), set.getValue());
                            }
                        }

                    }
                }
                //TODO
                //check if output list is empty . If it is not , must be a bad request
                if(output.size()!=0){
                    throw new CustomBadRequestException("Formatting errors",output);
                }

                RemittanceTransaction theRemittanceTransaction = oMapper.convertValue(transaction, RemittanceTransaction.class);
                Receiver theReceiver = oMapper.convertValue(receiver, Receiver.class);
                Sender theSender = oMapper.convertValue(sender, Sender.class);
                Address theAddressR = oMapper.convertValue(addressR, Address.class);
                Address theAddressS = oMapper.convertValue(addressS, Address.class);
                BankAccount theBankAccountR = oMapper.convertValue(bankAccountR, BankAccount.class);
                BankAccount theBankAccountS = oMapper.convertValue(bankAccountS, BankAccount.class);
                Identification theIdentificationR = oMapper.convertValue(identificationR, Identification.class);
                Identification theIdentificationS = oMapper.convertValue(identificationS, Identification.class);

                theReceiver.setAddress(theAddressR);
                theReceiver.setBankAccount(theBankAccountR);
                theReceiver.setIdentification(theIdentificationR);

                theSender.setAddress(theAddressS);
                theSender.setBankAccount(theBankAccountS);
                theSender.setIdentification(theIdentificationS);

                theRemittanceTransaction.setReceiver(theReceiver);
                theRemittanceTransaction.setSender(theSender);
                theRemittanceTransaction.setCompany(theCompany);
                theRemittanceTransaction.setRemittanceCompany(RemittanceCompanyName.valueOf(remittanceCompany));
                SandboxResponse response = null;

                sendToSandbox(remittanceCompany, output, counter, theRemittanceTransaction);

                counter++;
            }
        } else {
            throw new CustomNotFoundException("Did not find remittance map for - " + destCountry);
        }

        return output;

    }


    private void mapParty(ObjectMapper oMapper, Map<String, String> party, Map<String, String> address,
                          Map<String, String> bankAccountS, Map<String, String> identificationS,
                          Map.Entry<String, String> transactionSet, PartyMap partyMap, Map.Entry<String, String> partySet,
                        int counter , ArrayList output)
    {

        if (!(partySet.getKey().equals("id"))) {
            if (partySet.getKey().equals("addressMap")) {
                AddressMap theAddressMap = partyMap.getAddressMap();
                Map<String, String> addressMap = oMapper.convertValue(theAddressMap, Map.class);
                for (Map.Entry<String, String> setAS : addressMap.entrySet()) {
                    if (!(setAS.getKey().equals("id"))) {
                        addFields(address, setAS, transactionSet);
                    }
                }
            } else if (partySet.getKey().equals("bankAccountMap")) {
                BankAccountMap theBankAccountMap = partyMap.getBankAccountMap();
                Map<String, String> bankAccountMap = oMapper.convertValue(theBankAccountMap, Map.class);
                for (Map.Entry<String, String> setBS : bankAccountMap.entrySet()) {
                    if (!(setBS.getKey().equals("id"))) {
                        //TODO
                        //if setRM.getKey() is accountNumber
                        // check if alphanumeric string
                        addFields(bankAccountS, setBS, transactionSet);
                    }
                }
            } else if (partySet.getKey().equals("identificationMap")) {
                IdentificationMap theIdentificationMap = partyMap.getIdentificationMap();
                Map<String, String> identificationMap = oMapper.convertValue(theIdentificationMap, Map.class);
                for (Map.Entry<String, String> setIS : identificationMap.entrySet()) {
                    if (!(setIS.getKey().equals("id"))) {

                        //TODO
                        //if setRM.getKey() is issuingCountry
                        // check if 3 letter ALL caps  string

                        //TODO
                        //if setRM.getKey() is idNumber
                        // check if alphanumeric  string

                        if (setIS.getKey().equals("idNumber")){
                            if(!isAlphaNumeric(setIS.getValue())){
                                output.add("Transaction " + counter + ": error due to " + "idNumber is not AlphaNumeric");
                            }
                        }

                        addFields(identificationS, setIS, transactionSet);
                    }
                }
            } else {
                //TODO
                //if setRM.getKey() is firstName & lastname
                // check if in english
                if(partySet.getKey()=="firstName" ||partySet.getKey()=="lastName" ){
                    if(languageDetector.detectLanguageOf(transactionSet.getValue())!= Language.ENGLISH){
                        output.add("Transaction " + counter + ": error due to " + "names must be in English" );
                    }
                }

                addFields(party, partySet, transactionSet);
            }
        }
    }
    private void mapSender(RemittanceMap theRemittanceMap, ObjectMapper oMapper, Map<String, String> sender,
                           Map<String, String> addressS, Map<String, String> bankAccountS,
                           Map<String, String> identificationS, Map.Entry<String, String> set , int counter , ArrayList output)
    {
        SenderMap theSenderMap = theRemittanceMap.getSenderMap();
        Map<String, String> senderMap = oMapper.convertValue(theSenderMap, Map.class);
        for (Map.Entry<String, String> senderSet : senderMap.entrySet()) {
            mapParty(oMapper, sender, addressS, bankAccountS, identificationS, set, (PartyMap) theSenderMap, senderSet, counter, output);
        }
    }

    private void mapReceiver(RemittanceMap theRemittanceMap, ObjectMapper oMapper, Map<String, String> receiver,
                             Map<String, String> addressR, Map<String, String> bankAccountR,
                             Map<String, String> identificationR, Map.Entry<String, String> set , int counter , ArrayList output)
    {
        ReceiverMap theReceiverMap = theRemittanceMap.getReceiverMap();
        Map<String, String> receiverMap = oMapper.convertValue(theReceiverMap, Map.class);
        for (Map.Entry<String, String> receiverSet : receiverMap.entrySet()) {
            mapParty(oMapper, receiver, addressR, bankAccountR, identificationR, set, (PartyMap) theReceiverMap, receiverSet , counter , output);
        }
    }


    private static void addFields(Map<String, String> transactionData, Map.Entry<String, String> setSSOT, Map.Entry<String, String> setCSVTransaction) {
        if (setCSVTransaction.getKey().equals(setSSOT.getValue())) {
            transactionData.put(setSSOT.getKey(), setCSVTransaction.getValue());
        }
    }
    private void sendToSandbox(String remittanceCompany, List<String> output, int counter, RemittanceTransaction theRemittanceTransaction) throws IOException {
        SandboxResponse response;
        switch (remittanceCompany) {
            case "EVERYWHERE_REMIT":
                EverywhereRemitData everywhereRemitTransaction = ssotToEverywhereRemitMapper.MapSSOT(theRemittanceTransaction);
                response = sandboxAPIService.sendTransactionToSandbox(everywhereRemitTransaction, "everywhereremit");
                updateStatus(output, counter, theRemittanceTransaction, response);
                break;

            case "FINANCE_NOW":
                FinanceNowData financeNowTransaction = ssotToFinanceNowMapper.MapSSOT(theRemittanceTransaction);
                response = sandboxAPIService.sendTransactionToSandbox(financeNowTransaction, "financenow");
                updateStatus(output, counter, theRemittanceTransaction, response);
                break;

            case "PAYMENT_GO":
                PaymentGoData paymentGoTransaction = ssotToPaymentGoMapper.MapSSOT(theRemittanceTransaction);
                response = sandboxAPIService.sendTransactionToSandbox(paymentGoTransaction, "paymentgo");
                updateStatus(output, counter, theRemittanceTransaction, response);
                break;

        }
    }

    private void updateStatus(List<String> output, int counter, RemittanceTransaction theRemittanceTransaction, SandboxResponse response) {
        if (response.getCode() == 1) {
            if (response.getMessage().contains("Rejected")) {
                theRemittanceTransaction.setTransactionStatus(TransactionStatus.REJECTED);
            } else if (response.getMessage().contains("Pending AML")) {
                theRemittanceTransaction.setTransactionStatus(TransactionStatus.PENDING_AML);
            } else if (response.getMessage().contains("Pending Compliance Checks")) {
                theRemittanceTransaction.setTransactionStatus(TransactionStatus.PENDING_COMPLIANCE_CHECKS);
            } else {
                theRemittanceTransaction.setTransactionStatus(TransactionStatus.SUCCESSFUL);
            }
            remittanceTransactionDAO.save(theRemittanceTransaction);
        } else {
            output.add("Transaction " + counter + ": error due to " + response.getError());
        }
    }

    private boolean isAlphaNumeric(String s) {
        return s != null && s.matches("^[a-zA-Z0-9]*$");
    }




}
