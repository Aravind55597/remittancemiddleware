package com.remittancemiddleware.remittancemiddleware.dataclass.remittance.paymentgo.enumdata;

public enum SourceOfFundsPG {

    BUSINESS_AND_INVESTMENT("02"),
    CRYPTOCURRENCY_OR_OTHER_DIGITAL_PAYMENT_TOKENS("07"),
    DONATION("06"),
    FRIENDS_AND_FAMILY("04"),
    OTHER("99"),
    SALARY_TO_INCLUDE_ANY_WORK_RELATED_COMPENSATION_AND_PENSIONS("01");

    private String data;

    SourceOfFundsPG(String data) {
        this.data = data;
    }

    public String data() {
        return data;
    }
}
