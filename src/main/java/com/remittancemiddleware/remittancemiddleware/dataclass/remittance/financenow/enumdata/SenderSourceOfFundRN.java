package com.remittancemiddleware.remittancemiddleware.dataclass.remittance.financenow.enumdata;

public enum SenderSourceOfFundRN {

    BUSINESS_AND_INVESTMENT("02"),
    DIGITAL_PAYMENT_TOKENS("07"),
    DONATION("06"),
    FRIENDS_AND_FAMILY("04"),
    OTHER("99"),
    SALARY_COMPENSATION_PENSION("01");
    ;

    private String data;

    SenderSourceOfFundRN(String data) {
        this.data = data;
    }

    public String data() {
        return data;
    }

}
