package com.remittancemiddleware.remittancemiddleware.dataclass.remittance.everywhereremit.enumdata;

public enum SenderSourceOfFundRN {

    INVESTMENT("03"),
    REAL_ESTATE("07"),
    BUSINESS_REVENUE("08"),
    FRIENDS_AND_FAMILY("02"),
    BANK_DEPOSIT("01"),
    ALLOWANCE("04"),
    LOAN("05"),
    SALARY("06"),
    ;

    private final String data;

    SenderSourceOfFundRN(String data) {
        this.data = data;
    }

    public String data() {
        return data;
    }

}
