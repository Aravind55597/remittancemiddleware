package com.remittancemiddleware.remittancemiddleware.dataclass.remittance.paymentgo;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentGoData {
    private String remitGivenName;

    private String remitSurname;

    private String remitCaType;

    private String remitCaNo;

    private long merTransAmount;

    private String remitCountryCode;

    private String remitAddress;

    private String nationality;

    private String remitPurpose;

    private String payeeCaType;

    private String settleCurrency;

    private String transCurrency;

    private String payeeGivenName;

    private String payeeSurname;

    private String payeeBirthDate;

    private String payeeAccountNo;

    private String payeePhone;

    private String payeeBankName;

    private String payeeBranchName;

    private String payeeCaNo;

    private String remitAccountNo;

    private String sourceOfFunds;

}
