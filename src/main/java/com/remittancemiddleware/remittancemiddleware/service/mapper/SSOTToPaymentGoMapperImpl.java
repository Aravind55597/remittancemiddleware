package com.remittancemiddleware.remittancemiddleware.service.mapper;

import com.remittancemiddleware.remittancemiddleware.customexception.CustomMappingException;
import com.remittancemiddleware.remittancemiddleware.dataclass.remittance.paymentgo.PaymentGoData;
import com.remittancemiddleware.remittancemiddleware.dataclass.remittance.paymentgo.enumdata.IdTypePG;
import com.remittancemiddleware.remittancemiddleware.dataclass.remittance.paymentgo.enumdata.PurposeOfRemittancePG;
import com.remittancemiddleware.remittancemiddleware.dataclass.remittance.paymentgo.enumdata.SourceOfFundsPG;
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
public class SSOTToPaymentGoMapperImpl implements  SSOTToPaymentGoMapper{

    private SimpleDateFormat simpleDateFormat;

    @Autowired
    public SSOTToPaymentGoMapperImpl(SimpleDateFormat simpleDateFormat) {
        this.simpleDateFormat = simpleDateFormat;
    }

    @Override
    public PaymentGoData MapSSOT(RemittanceTransaction ssot) throws CustomMappingException {
        try{
            PaymentGoData result = new PaymentGoData();

            //sender
            result.setRemitGivenName(ssot.getSender().getFirstName());
            result.setRemitSurname(ssot.getSender().getLastName());
            result.setRemitCaType(this.convertIdType(ssot.getSender().getIdentification().getIdType()));
            result.setRemitCaNo(ssot.getSender().getIdentification().getIdNumber());
            result.setRemitCountryCode(ssot.getSender().getAddress().getCountry());
            result.setRemitAddress(ssot.getSender().getAddress().getAddressLine());
            result.setNationality(ssot.getSender().getNationality());
            result.setRemitAccountNo(ssot.getSender().getBankAccount().getAccountNumber());

            result.setRemitPurpose(this.convertRemittancePurpose(ssot.getPurpose()));
            result.setSourceOfFunds(this.convertSourceOfFunds(ssot.getSender().getSourceOfFunds()));

            //source Currency
            result.setSettleCurrency(ssot.getSender().getCurrency());

            //destination currency
            result.setTransCurrency(ssot.getReceiver().getCurrency());

            //receiver
            result.setPayeeCaType(this.convertIdType(ssot.getSender().getIdentification().getIdType()));
            result.setPayeeGivenName(ssot.getReceiver().getFirstName());
            result.setPayeeSurname(ssot.getReceiver().getLastName());
            result.setPayeeBirthDate(simpleDateFormat.format(ssot.getReceiver().getDateOfBirth()));
            result.setPayeeAccountNo(ssot.getReceiver().getBankAccount().getAccountNumber());
            result.setPayeePhone(ssot.getReceiver().getMobileNumber());
            result.setPayeeBankName(ssot.getReceiver().getBankAccount().getBankName());
            result.setPayeeBranchName(ssot.getReceiver().getBankAccount().getBranchName());
            result.setPayeeCaNo(ssot.getReceiver().getIdentification().getIdNumber());

            result.setMerTransAmount(ssot.getAmount());

            return result;
        }
        //if SSOT has a null value
        catch(NullPointerException ex){

            throw new CustomMappingException(ex.getMessage());
        }
    }

    public String convertIdType(IdType idType) {
        switch (idType) {
            case NATIONAL_ID:
                return IdTypePG.NATIONAL_ID.data();
            case PASSPORT:
                return IdTypePG.PASSPORT.data();
            default:
                return null;
        }
    }

    @Override
    public String convertSourceOfFunds(SourceOfFunds sourceOfFunds) {
        switch (sourceOfFunds) {
            case INVESTMENT:
            case BUSINESS_REVENUE:
                return SourceOfFundsPG.BUSINESS_AND_INVESTMENT.data();
            case DIGITAL_PAYMENT_TOKENS:
                return SourceOfFundsPG.CRYPTOCURRENCY_OR_OTHER_DIGITAL_PAYMENT_TOKENS.data();
            case DONATION:
                return SourceOfFundsPG.DONATION.data();
            case FRIENDS_AND_FAMILY:
                return SourceOfFundsPG.FRIENDS_AND_FAMILY.data();
            case ALLOWANCE:
            case SALARY:
                return SourceOfFundsPG.SALARY_TO_INCLUDE_ANY_WORK_RELATED_COMPENSATION_AND_PENSIONS.data();
            default:
                return SourceOfFundsPG.OTHER.data();
        }


    }


    public String convertRemittancePurpose(RemittancePurpose remittancePurpose) {
        switch (remittancePurpose) {
            case FAMILY_EXPENSES:
                return PurposeOfRemittancePG.FAMILY_EXPENSES.data();
            case CHARITY_DONATION:
                return PurposeOfRemittancePG.CHARITY_DONATION.data();
            case PAYMENT_FOR_SERVICES:
                return PurposeOfRemittancePG.PAYMENT_FOR_SERVICES.data();
            case PERSONAL_ASSET_ALLOCATION:
                return PurposeOfRemittancePG.PERSONAL_ASSET_ALLOCATION.data();
            case PAYMENT_FOR_GOODS:
                return PurposeOfRemittancePG.PAYMENT_FOR_GOODS.data();
            case CAPITAL_TRANSFER:
                return PurposeOfRemittancePG.CAPITAL_TRANSFER.data();
            case INVESTMENT:
                return PurposeOfRemittancePG.INVESTMENT.data();
            case EMPLOYEE_PAYROLL:
                return PurposeOfRemittancePG.EMPLOYEE_PAYROLL.data();
            case GOODS_TRADE:
                return PurposeOfRemittancePG.GOODS_TRADE.data();
            case SERVICES_TRADE:
                return PurposeOfRemittancePG.SERVICES_TRADE.data();
            case RETURN_OF_EXPORT_TRADE:
                return PurposeOfRemittancePG.RETURN_OF_EXPORT_TRADE.data();
            case LAND_CONSTRUCTION_MORTGAGE_RELATED_PAYMENTS:
                return PurposeOfRemittancePG.LAND_CONSTRUCTION_MORTGAGE_RELATED_PAYMENTS.data();
            case EDUCATION:
                return PurposeOfRemittancePG.EDUCATION.data();
            case BUSINESS_EXPENSES:
                return PurposeOfRemittancePG.BUSINESS_EXPENSES.data();
            case SELF:
                return PurposeOfRemittancePG.SELF.data();
            default:
                return PurposeOfRemittancePG.OTHER.data();
        }
    }

}
