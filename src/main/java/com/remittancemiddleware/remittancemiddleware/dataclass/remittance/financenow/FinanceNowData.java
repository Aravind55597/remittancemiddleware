package com.remittancemiddleware.remittancemiddleware.dataclass.remittance.financenow;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
public class FinanceNowData {

    private String bankAccountNumber;

    private String paymentAmount;

    private String paymentMode;

    private String payoutCurrency;

    //Data from PurposeOfRemittance enum
    private String purposeOfRemittance;

    private String receiverAddress;

    private String receiverCity;

    private String receiverCountry;

    private String receiverFirstName;

    private String receiverIdNumber;

    private String receiverIdType;

    private String receiverLastName;

    private String senderAddress;

    private String senderBeneficiaryRelationship;

    private String senderCity;

    private String senderCountry;

    private String senderDateOfBirth;

    private String senderFirstName;

    private String senderIdNumber;

    private String senderIdType;

    private String senderLastName;

    private String senderNationality;

    private String senderSourceOfFund;

    private String senderState;

    private String receiverNationality;


}
