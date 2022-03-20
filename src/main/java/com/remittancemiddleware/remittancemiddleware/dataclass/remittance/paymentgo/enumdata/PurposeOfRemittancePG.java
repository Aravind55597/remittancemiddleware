package com.remittancemiddleware.remittancemiddleware.dataclass.remittance.paymentgo.enumdata;

public enum PurposeOfRemittancePG {

    FAMILY_EXPENSES("1"), CHARITY_DONATION("2"), PAYMENT_FOR_SERVICES("3"), TRAVEL_EXPENSES("4"),
    PERSONAL_ASSET_ALLOCATION("5"), PAYMENT_FOR_GOODS("6"), CAPITAL_TRANSFER("7"), INVESTMENT("8"),
    EMPLOYEE_PAYROLL("9"), GOODS_TRADE("10"), SERVICES_TRADE("11"), RETURN_OF_EXPORT_TRADE("12"),
    LAND_CONSTRUCTION_MORTGAGE_RELATED_PAYMENTS("13"), EDUCATION("14"), SELF("15"),BUSINESS_EXPENSES("16"),
    OTHER("17");

    private String data;

    PurposeOfRemittancePG(String data) {
        this.data = data;
    }

    public String data() {
        return data;
    }

}
