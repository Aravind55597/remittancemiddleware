package com.remittancemiddleware.remittancemiddleware.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.remittancemiddleware.remittancemiddleware.customexception.CustomNotFoundException;
import com.remittancemiddleware.remittancemiddleware.dao.RemittanceTransactionDAO;
import com.remittancemiddleware.remittancemiddleware.dao.UserDAO;
import com.remittancemiddleware.remittancemiddleware.dao.CompanyDAO;
import com.remittancemiddleware.remittancemiddleware.dao.RemittanceMapDAO;
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

import com.remittancemiddleware.remittancemiddleware.service.mapper.SSOTToEverywhereRemitMapperImpl;
import com.remittancemiddleware.remittancemiddleware.service.mapper.SSOTToFinanceNowMapperImpl;
import com.remittancemiddleware.remittancemiddleware.service.mapper.SSOTToPaymentGoMapperImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class RemittanceTransactionServiceImpl implements RemittanceTransactionService{
    private RemittanceTransactionDAO remittanceTransactionDAO;
    private UserDAO userDAO;
    private CompanyDAO companyDAO;
    private RemittanceMapDAO remittanceMapDAO;
    private CsvProcessorServiceImpl csvProcessorServiceImpl;
    private SSOTToEverywhereRemitMapperImpl ssotToEverywhereRemitMapperImpl;
    private SSOTToFinanceNowMapperImpl ssotToFinanceNowMapperImpl;
    private SSOTToPaymentGoMapperImpl ssotToPaymentGoMapperImpl;
    private SandboxAPIServiceImpl sandboxAPIServiceImpl;

    public RemittanceTransactionServiceImpl (RemittanceTransactionDAO theRemittanceTransactionDAO, UserDAO theUserDAO, CompanyDAO theCompanyDAO, RemittanceMapDAO theRemittanceMapDAO, CsvProcessorServiceImpl theCsvProcessorServiceImpl, SSOTToEverywhereRemitMapperImpl theSSOTToEverywhereRemitMapperImpl, SSOTToFinanceNowMapperImpl theSSOTToFinanceNowMapperImpl, SSOTToPaymentGoMapperImpl theSSOTToPaymentGoMapperImpl, SandboxAPIServiceImpl theSandboxAPIServiceImpl){
        this.remittanceTransactionDAO = theRemittanceTransactionDAO;
        this.userDAO = theUserDAO;
        this.companyDAO = theCompanyDAO;
        this.remittanceMapDAO = theRemittanceMapDAO;
        this.csvProcessorServiceImpl = theCsvProcessorServiceImpl;
        this.ssotToEverywhereRemitMapperImpl = theSSOTToEverywhereRemitMapperImpl;
        this.ssotToFinanceNowMapperImpl = theSSOTToFinanceNowMapperImpl;
        this.ssotToPaymentGoMapperImpl = theSSOTToPaymentGoMapperImpl;
        this.sandboxAPIServiceImpl = theSandboxAPIServiceImpl;
    }

    @Override
    public List<RemittanceTransaction> findByTransactionStatusAndCompanyId(TransactionStatus status, int userId) throws CustomNotFoundException {

        Optional<User> result = userDAO.findById(userId);

        User theUser = null;

        if (result.isPresent()) {
            theUser = result.get();
        }
        else {
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
        }
        else {
            // we didn't find the user
            throw new CustomNotFoundException("Did not find user id - " + userId);
        }
        Company company = theUser.getCompany();
        int companyId = company.getId();

        return remittanceTransactionDAO.findByCompanyId(companyId);
    }

    @Override
    public TransactionStatus getTransactionStatus(String status) throws CustomNotFoundException{
        TransactionStatus transactionStatus = null;
        if (status.equals("SUCCESSFUL")){
            transactionStatus = TransactionStatus.SUCCESSFUL;
        } else if (status.equals("PENDING_AML")){
            transactionStatus = TransactionStatus.PENDING_AML;
        } else if (status.equals("PENDING_COMPLIANCE_CHECKS")){
            transactionStatus = TransactionStatus.PENDING_COMPLIANCE_CHECKS;
        } else if (status.equals("REJECTED")){
            transactionStatus = TransactionStatus.REJECTED;
        } else{
            throw new CustomNotFoundException("Incorrect status input - " + status);
        }

        return transactionStatus;
    }

    @Override
    public List<String> processTransactions(@PathVariable int userId, @PathVariable String destCountry, @PathVariable String remittanceCompany, MultipartFile csvFile) throws Exception {
        User theUser = userDAO.getById(userId);
        int companyId = theUser.getCompanyId();
        Company theCompany = companyDAO.getById(companyId);
        List<String> output = new ArrayList<>();

        Optional<RemittanceMap> result = Optional.ofNullable(remittanceMapDAO.findByCompanyAndDestinationCountry(theCompany, destCountry));

        RemittanceMap theRemittanceMap = null;


        if (result.isPresent()) {
            theRemittanceMap = result.get();
            List<Map<String,String>> transactions = csvProcessorServiceImpl.processCsv(csvFile);
            ObjectMapper oMapper = new ObjectMapper();
            Map<String,String> remittanceMap = oMapper.convertValue(theRemittanceMap, Map.class);

            for (Map<String,String> t:transactions) {
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
                                ReceiverMap theReceiverMap = theRemittanceMap.getReceiverMap();
                                Map<String,String> receiverMap = oMapper.convertValue(theReceiverMap, Map.class);
                                for (Map.Entry<String,String> setR : receiverMap.entrySet()) {
                                    if (!(setR.getKey().equals("id"))) {
                                        if (setR.getKey().equals("addressMap")) {
                                            AddressMap theAddressMap = theReceiverMap.getAddressMap();
                                            Map<String,String> addressMap = oMapper.convertValue(theAddressMap, Map.class);
                                            for (Map.Entry<String,String> setAR : addressMap.entrySet()) {
                                                if (!(setAR.getKey().equals("id"))) {
                                                    addFields(addressR, setAR, set);
                                                }

                                            }
                                        }
                                        else if (setR.getKey().equals("bankAccountMap")) {
                                            BankAccountMap theBankAccountMap = theReceiverMap.getBankAccountMap();
                                            Map<String,String> bankAccountMap = oMapper.convertValue(theBankAccountMap, Map.class);
                                            for (Map.Entry<String,String> setBR : bankAccountMap.entrySet()) {
                                                if (!(setBR.getKey().equals("id"))) {
                                                    addFields(bankAccountR, setBR, set);
                                                }
                                            }
                                        }
                                        else if (setR.getKey().equals("identificationMap")) {
                                            IdentificationMap theIdentificationMap = theReceiverMap.getIdentificationMap();
                                            Map<String,String> identificationMap = oMapper.convertValue(theIdentificationMap, Map.class);
                                            for (Map.Entry<String,String> setIR : identificationMap.entrySet()) {
                                                if (!(setIR.getKey().equals("id"))) {
                                                    addFields(identificationR, setIR, set);
                                                }
                                            }
                                        }
                                        else {
                                            addFields(receiver, setR, set);
                                        }
                                    }
                                }
                            }
                            else if (setRM.getKey().equals("senderMap")) {
                                SenderMap theSenderMap = theRemittanceMap.getSenderMap();
                                Map<String,String> senderMap = oMapper.convertValue(theSenderMap, Map.class);
                                for (Map.Entry<String,String> setS : senderMap.entrySet()) {
                                    if (!(setS.getKey().equals("id"))) {
                                        if (setS.getKey().equals("addressMap")) {
                                            AddressMap theAddressMap = theSenderMap.getAddressMap();
                                            Map<String,String> addressMap = oMapper.convertValue(theAddressMap, Map.class);
                                            for (Map.Entry<String,String> setAS : addressMap.entrySet()) {
                                                if (!(setAS.getKey().equals("id"))) {
                                                    addFields(addressS, setAS, set);
                                                }
                                            }
                                        }
                                        else if (setS.getKey().equals("bankAccountMap")) {
                                            BankAccountMap theBankAccountMap = theSenderMap.getBankAccountMap();
                                            Map<String,String> bankAccountMap = oMapper.convertValue(theBankAccountMap, Map.class);
                                            for (Map.Entry<String,String> setBS : bankAccountMap.entrySet()) {
                                                if (!(setBS.getKey().equals("id"))) {
                                                    addFields(bankAccountS, setBS, set);
                                                }
                                            }
                                        }
                                        else if (setS.getKey().equals("identificationMap")) {
                                            IdentificationMap theIdentificationMap = theSenderMap.getIdentificationMap();
                                            Map<String,String> identificationMap = oMapper.convertValue(theIdentificationMap, Map.class);
                                            for (Map.Entry<String,String> setIS : identificationMap.entrySet()) {
                                                if (!(setIS.getKey().equals("id"))) {
                                                    addFields(identificationS, setIS, set);
                                                }
                                            }
                                        }
                                        else {
                                            addFields(sender, setS, set);
                                        }
                                    }
                                }

                            }
                            else if (set.getKey().equals(setRM.getValue())) {
                                transaction.put(setRM.getKey(),set.getValue());
                            }
                        }

                    }
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

                switch (remittanceCompany) {
                    case "EVERYWHERE_REMIT":
                        SSOTToEverywhereRemitMapperImpl ssotToEverywhereRemitMapperImpl = new SSOTToEverywhereRemitMapperImpl(new SimpleDateFormat());
                        EverywhereRemitData everywhereRemitTransaction = ssotToEverywhereRemitMapperImpl.MapSSOT(theRemittanceTransaction);
                        response = sandboxAPIServiceImpl.sendTransactionToSandbox(everywhereRemitTransaction, "everywhereremit");
                        if (response.getCode() == 1) {
                            if (response.getMessage().contains("Rejected")) {
                                theRemittanceTransaction.setTransactionStatus(TransactionStatus.REJECTED);
                            }
                            else if (response.getMessage().contains("Pending AML")) {
                                theRemittanceTransaction.setTransactionStatus(TransactionStatus.PENDING_AML);
                            }
                            else if (response.getMessage().contains("Pending Compliance Checks")) {
                                theRemittanceTransaction.setTransactionStatus(TransactionStatus.PENDING_COMPLIANCE_CHECKS);
                            }
                            else{
                                theRemittanceTransaction.setTransactionStatus(TransactionStatus.SUCCESSFUL);
                            }
                            remittanceTransactionDAO.save(theRemittanceTransaction);
                        }

                        else {
                            output.add("Transaction " + Integer.toString(counter) + ": error due to " + response.getError());
                        }


                        break;

                    case "FINANCE_NOW":
                        SSOTToFinanceNowMapperImpl ssotToFinanceNowMapperImpl = new SSOTToFinanceNowMapperImpl(new SimpleDateFormat());
                        FinanceNowData financeNowTransaction = ssotToFinanceNowMapperImpl.MapSSOT(theRemittanceTransaction);
                        response = sandboxAPIServiceImpl.sendTransactionToSandbox(financeNowTransaction, "financenow");
                        break;

                    case "PAYMENT_GO":
                        SSOTToPaymentGoMapperImpl ssotToPaymentGoMapperImpl = new SSOTToPaymentGoMapperImpl(new SimpleDateFormat());
                        PaymentGoData paymentGoTransaction = ssotToPaymentGoMapperImpl.MapSSOT(theRemittanceTransaction);
                        response = sandboxAPIServiceImpl.sendTransactionToSandbox(paymentGoTransaction, "paymentgo");
                        break;

                }

                counter ++;
            }
        }

        else {
            throw new CustomNotFoundException("Did not find remittance map for - " + destCountry);
        }

        return output;

    }

    private static void addFields(Map<String, String> transactionData, Map.Entry<String, String> setSSOT, Map.Entry<String, String> setCSVTransaction) {
        if (setCSVTransaction.getKey().equals(setSSOT.getValue())) {
            transactionData.put(setSSOT.getKey(),setCSVTransaction.getValue());
        }
    }



}
