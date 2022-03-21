package com.remittancemiddleware.remittancemiddleware.dataclass.remittance.everywhereremit;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class EverywhereRemitData {

    private String recipientAccountNumber; //

    private String recipientCurrency; //
    //Data from PurposeOfRemittance enum

    private String remittancePurpose;

    private String recipientCountry;

    private String recipientLegalNameFirst; //

    private String recipientLegalNameLast; //

    private String senderAddressLine; //

    private String senderAddressCity; //

    private String senderAddressCountry; //

    private String senderDateOfBirth; //

    private String senderLegalNameFirst; //

    private String senderIdNumber; //

    private String senderIdType; //

    private String senderLegalNameLast; //

    private String senderNationality;

    private String sourceOfFunds; //

    private String recipientMobileNumber; //

    private String units; //

    private String sourceType; //

    private String segment; //

    private String senderIdCountry; //

    private String senderCurrency; //

    private String recipientType; //

    private String senderCountry; // new field, misplaced previously in receiverCountry



}
