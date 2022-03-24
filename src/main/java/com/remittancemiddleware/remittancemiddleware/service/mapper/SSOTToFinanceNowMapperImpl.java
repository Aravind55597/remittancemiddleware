package com.remittancemiddleware.remittancemiddleware.service.mapper;

import com.remittancemiddleware.remittancemiddleware.customexception.CustomMappingException;
import com.remittancemiddleware.remittancemiddleware.dataclass.remittance.financenow.FinanceNowData;
import com.remittancemiddleware.remittancemiddleware.dataclass.remittance.financenow.enumdata.*;
import com.remittancemiddleware.remittancemiddleware.entity.enumdata.BeneficiaryRelationship;
import com.remittancemiddleware.remittancemiddleware.entity.enumdata.IdType;
import com.remittancemiddleware.remittancemiddleware.entity.enumdata.RemittancePurpose;
import com.remittancemiddleware.remittancemiddleware.entity.enumdata.SourceOfFunds;
import com.remittancemiddleware.remittancemiddleware.entity.transaction.RemittanceTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.text.SimpleDateFormat;

@Service
@Transactional
public class SSOTToFinanceNowMapperImpl implements SSOTToFinanceNowMapper {


    private SimpleDateFormat simpleDateFormat;

    @Autowired
    public SSOTToFinanceNowMapperImpl(SimpleDateFormat simpleDateFormat){
        this.simpleDateFormat=simpleDateFormat;
    }


    @Override
    public FinanceNowData MapSSOT(RemittanceTransaction ssot) throws CustomMappingException{
        try{
            FinanceNowData result = new FinanceNowData();

            this.setRemittanceValues(ssot,result);

            this.setSenderValues(ssot,result);

            this.setReceiverValues(ssot,result);

            return result;
        }
        //if SSOT has a null value
        catch(NullPointerException ex){

            throw new CustomMappingException(ex.getMessage());
        }
    }



    void setSenderValues(RemittanceTransaction ssot, FinanceNowData result) throws NullPointerException , CustomMappingException {
        result.setSenderFirstName(ssot.getSender().getFirstName());

        result.setSenderLastName(ssot.getSender().getLastName());

        result.setSenderDateOfBirth(simpleDateFormat.format(ssot.getSender().getDateOfBirth()));

        result.setSenderNationality(this.convertCountry(ssot.getSender().getNationality()));

        result.setSenderIdType(this.convertIdType(ssot.getSender().getIdentification().getIdType()));

        result.setSenderIdNumber(ssot.getSender().getIdentification().getIdNumber());

        result.setSenderAddress(ssot.getSender().getAddress().getAddressLine());

        result.setSenderCity(ssot.getSender().getAddress().getCity());

        result.setSenderState(ssot.getSender().getAddress().getState());

        result.setSenderCountry(this.convertCountry(ssot.getSender().getAddress().getCountry()));



        result.setSenderBeneficiaryRelationship(this.convertBeneficiaryRelationship(ssot.getSender().getBeneficiaryRelationship()));

        result.setSenderSourceOfFund(this.convertSourceOfFunds(ssot.getSender().getSourceOfFunds()));

        result.setPayoutCurrency(ssot.getReceiver().getPayoutCurrency());

    }

    void setReceiverValues(RemittanceTransaction ssot, FinanceNowData result) throws NullPointerException , CustomMappingException{

        result.setReceiverFirstName(ssot.getReceiver().getFirstName());

        result.setReceiverLastName(ssot.getReceiver().getLastName());

        result.setReceiverNationality(this.convertCountry(ssot.getReceiver().getNationality()));

        result.setReceiverIdType(this.convertIdType(ssot.getReceiver().getIdentification().getIdType()));

        result.setReceiverIdNumber(ssot.getReceiver().getIdentification().getIdNumber());

        result.setReceiverAddress(ssot.getReceiver().getAddress().getAddressLine());

        result.setReceiverCity(ssot.getReceiver().getAddress().getCity());

        result.setReceiverCountry(this.convertCountry(ssot.getReceiver().getAddress().getCountry()));

        result.setBankAccountNumber(ssot.getReceiver().getBankAccount().getAccountNumber());

    }


    void setRemittanceValues(RemittanceTransaction ssot, FinanceNowData result) throws NullPointerException , CustomMappingException{

        result.setPaymentMode(ssot.getPaymentMode());

        result.setPurposeOfRemittance(this.convertRemittancePurpose(ssot.getPurpose()));

        result.setPaymentAmount(ssot.getAmount().toString());
    }

    public String convertCountry(String countrySSOT) throws CustomMappingException {

        try {
            CountryRN countryRN = CountryRN.valueOf(countrySSOT);
            return countryRN.toString();

        } catch (IllegalArgumentException ex) {
            //when string that parses to enum value does not exists
            throw new CustomMappingException(ex.getMessage());
        }
    }

    public String convertIdType(IdType idType) {
        switch (idType) {
            case COMPANY_REGISTRATION:
                return IdTypeRN.COMPANY_REGISTRATION.data();
            case NATIONAL_ID:
                return IdTypeRN.ID_CARD_GOVERNMENT.data();
            case PASSPORT:
                return IdTypeRN.PASSPORT.data();
            default:
                return IdTypeRN.OTHER.data();
        }
    }

    public String convertRemittancePurpose(RemittancePurpose remittancePurpose) {
        switch (remittancePurpose) {
            case FAMILY_EXPENSES:
                return PurposeOfRemittanceRN.FAMILY_EXPENSES.data();
            case CHARITY_DONATION:
                return PurposeOfRemittanceRN.DONATION.data();
            case PAYMENT_FOR_SERVICES:
            case PAYMENT_FOR_GOODS:
                return PurposeOfRemittanceRN.PAYMENT_PRODUCT_SERVICES.data();
            case LAND_CONSTRUCTION_MORTGAGE_RELATED_PAYMENTS:
                return PurposeOfRemittanceRN.CONSTRUCTION_LAND_PURCHASE_MORTGAGE_REPAYMENTS.data();
            case EDUCATION:
                return PurposeOfRemittanceRN.EDUCATION.data();
            case BUSINESS_EXPENSES:
                return PurposeOfRemittanceRN.BUSINESS_EXPENSES.data();
            case SELF:
                return PurposeOfRemittanceRN.SELF.data();
            default:
                return PurposeOfRemittanceRN.OTHER.data();
        }
    }

    public String convertBeneficiaryRelationship(BeneficiaryRelationship beneficiaryRelationship) {
        switch (beneficiaryRelationship) {
            case EMPLOYEE:
                return SenderBeneficiaryRelationshipRN.EMPLOYEE.data();
            case FAMILY:
                return SenderBeneficiaryRelationshipRN.FAMILY.data();
            case FRIENDS:
                return SenderBeneficiaryRelationshipRN.FRIENDS.data();
            case SELLER:
                return SenderBeneficiaryRelationshipRN.SELLER_SERVICE_PROVIDER.data();
            case SELF:
                return SenderBeneficiaryRelationshipRN.SELF.data();
            default:
                return SenderBeneficiaryRelationshipRN.OTHERS.data();
        }


    }

    public String convertSourceOfFunds(SourceOfFunds sourceOfFunds) {
        switch (sourceOfFunds) {
            case INVESTMENT:
            case BUSINESS_REVENUE:
                return SenderSourceOfFundRN.BUSINESS_AND_INVESTMENT.data();
            case DIGITAL_PAYMENT_TOKENS:
                return SenderSourceOfFundRN.DIGITAL_PAYMENT_TOKENS.data();
            case DONATION:
                return SenderSourceOfFundRN.DONATION.data();
            case FRIENDS_AND_FAMILY:
                return SenderSourceOfFundRN.FRIENDS_AND_FAMILY.data();
            case SALARY:
            case ALLOWANCE:
                return SenderSourceOfFundRN.SALARY_COMPENSATION_PENSION.data();
            default:
                return SenderSourceOfFundRN.OTHER.data();
        }


    }
}
