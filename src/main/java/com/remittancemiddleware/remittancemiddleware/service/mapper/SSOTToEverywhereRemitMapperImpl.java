package com.remittancemiddleware.remittancemiddleware.service.mapper;

import com.remittancemiddleware.remittancemiddleware.customexception.CustomMappingException;
import com.remittancemiddleware.remittancemiddleware.dataclass.remittance.everywhereremit.EverywhereRemitData;
import com.remittancemiddleware.remittancemiddleware.dataclass.remittance.everywhereremit.enumdata.CountryRN;
import com.remittancemiddleware.remittancemiddleware.dataclass.remittance.everywhereremit.enumdata.IdTypeRN;
import com.remittancemiddleware.remittancemiddleware.dataclass.remittance.everywhereremit.enumdata.PurposeOfRemittanceRN;
import com.remittancemiddleware.remittancemiddleware.dataclass.remittance.everywhereremit.enumdata.SenderSourceOfFundRN;
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
public class SSOTToEverywhereRemitMapperImpl implements SSOTToEverywhereRemitMapper {

    private final SimpleDateFormat simpleDateFormat;

    @Autowired
    public SSOTToEverywhereRemitMapperImpl(SimpleDateFormat simpleDateFormat) {
        this.simpleDateFormat = simpleDateFormat;
    }


    @Override
    public EverywhereRemitData MapSSOT(RemittanceTransaction ssot) throws CustomMappingException {
        try {
            EverywhereRemitData result = new EverywhereRemitData();

            result.setRemittancePurpose(this.convertRemittancePurpose(ssot.getPurpose()));

            this.setSenderValues(ssot, result);

            this.setReceiverValues(ssot, result);

            this.setRemittanceValues(ssot, result);

            return result;
        }
        //if SSOT has a null value
        catch (NullPointerException ex) {

            throw new CustomMappingException(ex.getMessage());
        }
    }


    void setSenderValues(RemittanceTransaction ssot, EverywhereRemitData result) throws NullPointerException, CustomMappingException {
        result.setSenderAddressLine(ssot.getSender().getAddress().getAddressLine());

        result.setSenderAddressCity(ssot.getSender().getAddress().getCity());

        result.setSenderAddressCountry(ssot.getSender().getAddress().getCountry());

        result.setSenderDateOfBirth(simpleDateFormat.format(ssot.getSender().getDateOfBirth()));

        result.setSenderLegalNameFirst(ssot.getSender().getFirstName());

        result.setSenderIdNumber(ssot.getSender().getIdentification().getIdNumber());

        result.setSenderIdType(this.convertIdType(ssot.getSender().getIdentification().getIdType()));

        result.setSenderLegalNameLast(ssot.getSender().getLastName());

        result.setSenderNationality(this.convertCountry(ssot.getSender().getNationality()));

        result.setSourceOfFunds(this.convertSourceOfFunds(ssot.getSender().getSourceOfFunds()));

        result.setSenderIdCountry(ssot.getSender().getIdentification().getIssuingCountry());

        result.setSenderCurrency(ssot.getSender().getCurrency());

        result.setSenderCountry(ssot.getSender().getSenderRemittanceCountry());
    }

    void setReceiverValues(RemittanceTransaction ssot, EverywhereRemitData result) throws NullPointerException, CustomMappingException {
        result.setRecipientAccountNumber(ssot.getReceiver().getBankAccount().getAccountNumber());

        result.setRecipientCurrency(ssot.getReceiver().getCurrency());

        result.setRecipientCountry(ssot.getReceiver().getReceiverRemittanceCountry());

        result.setRecipientLegalNameFirst(ssot.getReceiver().getFirstName());

        result.setRecipientLegalNameLast(ssot.getReceiver().getLastName());

        result.setRecipientMobileNumber(ssot.getSender().getMobileNumber());

        result.setRecipientType(ssot.getReceiver().getType());

        result.setRecipientCountry(ssot.getReceiver().getReceiverRemittanceCountry());

    }


    void setRemittanceValues(RemittanceTransaction ssot, EverywhereRemitData result) throws NullPointerException, CustomMappingException {
        result.setSourceType(ssot.getSourceType());
        result.setSegment(ssot.getSegment());
        result.setUnits(ssot.getAmount());
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
            case PASSPORT:
                return IdTypeRN.PASSPORT.data();
            default:
                return IdTypeRN.ID_CARD_GOVERNMENT.data();
        }
    }

    public String convertRemittancePurpose(RemittancePurpose remittancePurpose) {
        switch (remittancePurpose) {
            case GOODS_TRADE:
                return PurposeOfRemittanceRN.GOODS_TRADE.data();
            case SERVICES_TRADE:
                return PurposeOfRemittanceRN.SERVICES_TRADE.data();
            case RETURN_OF_EXPORT_TRADE:
                return PurposeOfRemittanceRN.RETURN_OF_EXPORT_TRADE.data();

            case CHARITY_DONATION:
                return PurposeOfRemittanceRN.CHARITY_DONATION.data();

            case FAMILY_EXPENSES:
                return PurposeOfRemittanceRN.FAMILY_EXPENSES.data();

            case PERSONAL_ASSET_ALLOCATION:
                return PurposeOfRemittanceRN.PERSONAL_ASSET_ALLOCATION.data();

            case CAPITAL_TRANSFER:
                return PurposeOfRemittanceRN.CAPITAL_TRANSFER.data();

            case EMPLOYEE_PAYROLL:
                return PurposeOfRemittanceRN.EMPLOYEE_PAYROLL.data();

            case TRAVEL_EXPENSES:
                return PurposeOfRemittanceRN.TRAVEL_EXPENSES.data();

            case PAYMENT_FOR_GOODS:
                return PurposeOfRemittanceRN.PAYMENT_FOR_GOODS.data();

            case INVESTMENT:
            case LAND_CONSTRUCTION_MORTGAGE_RELATED_PAYMENTS:
                return PurposeOfRemittanceRN.INVESTMENT.data();

            default:
                return PurposeOfRemittanceRN.PAYMENT_FOR_SERVICES.data();
        }
    }


    public String convertSourceOfFunds(SourceOfFunds sourceOfFunds) {
        switch (sourceOfFunds) {
            case INVESTMENT:
                return SenderSourceOfFundRN.INVESTMENT.data();
            case REAL_ESTATE:
                return SenderSourceOfFundRN.REAL_ESTATE.data();
            case BUSINESS_REVENUE:
                return SenderSourceOfFundRN.BUSINESS_REVENUE.data();
            case FRIENDS_AND_FAMILY:
                return SenderSourceOfFundRN.FRIENDS_AND_FAMILY.data();
            case ALLOWANCE:
                return SenderSourceOfFundRN.ALLOWANCE.data();
            case LOAN:
                return SenderSourceOfFundRN.LOAN.data();
            case SALARY:
                return SenderSourceOfFundRN.SALARY.data();
            default:
                return SenderSourceOfFundRN.BANK_DEPOSIT.data();
        }


    }
}
