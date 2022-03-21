package com.remittancemiddleware.remittancemiddleware.dataclass.remittance.everywhereremit.enumdata;

public enum PurposeOfRemittanceRN {
    GOODS_TRADE("008-01"),
    SERVICES_TRADE("008-02"),
    RETURN_OF_EXPORT_TRADE("008-03"),
    CHARITY_DONATION("001-02"),
    FAMILY_EXPENSES("001-01"),
    PERSONAL_ASSET_ALLOCATION("004-01"),
    CAPITAL_TRANSFER("006-01"),
    EMPLOYEE_PAYROLL("007-01"),
    INVESTMENT("006-02"),
    TRAVEL_EXPENSES("003-01"),
    PAYMENT_FOR_SERVICES("002-02"),
    PAYMENT_FOR_GOODS("005-01"),
    ;

    private String data;

    PurposeOfRemittanceRN(String data) {
        this.data = data;
    }

    public String data() {
        return data;
    }
}
