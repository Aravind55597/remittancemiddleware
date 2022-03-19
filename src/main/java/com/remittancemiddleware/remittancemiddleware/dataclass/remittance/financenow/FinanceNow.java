package com.remittancemiddleware.remittancemiddleware.dataclass.remittance.financenow;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FinanceNow {

    private String bankAccountNumber;

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

    @Override
    public String toString() {
        return "FinanceNow{" +
                "bankAccountNumber='" + bankAccountNumber + '\'' +
                ", paymentMode='" + paymentMode + '\'' +
                ", payoutCurrency='" + payoutCurrency + '\'' +
                ", purposeOfRemittance='" + purposeOfRemittance + '\'' +
                ", receiverAddress='" + receiverAddress + '\'' +
                ", receiverCity='" + receiverCity + '\'' +
                ", receiverCountry='" + receiverCountry + '\'' +
                ", receiverFirstName='" + receiverFirstName + '\'' +
                ", receiverIdNumber='" + receiverIdNumber + '\'' +
                ", receiverIdType='" + receiverIdType + '\'' +
                ", receiverLastName='" + receiverLastName + '\'' +
                ", senderAddress='" + senderAddress + '\'' +
                ", senderBeneficiaryRelationship='" + senderBeneficiaryRelationship + '\'' +
                ", senderCity='" + senderCity + '\'' +
                ", senderCountry='" + senderCountry + '\'' +
                ", senderDateOfBirth='" + senderDateOfBirth + '\'' +
                ", senderFirstName='" + senderFirstName + '\'' +
                ", senderIdNumber='" + senderIdNumber + '\'' +
                ", senderIdType='" + senderIdType + '\'' +
                ", senderLastName='" + senderLastName + '\'' +
                ", senderNationality='" + senderNationality + '\'' +
                ", senderSourceOfFund='" + senderSourceOfFund + '\'' +
                ", senderState='" + senderState + '\'' +
                ", receiverNationality='" + receiverNationality + '\'' +
                '}';
    }
}
