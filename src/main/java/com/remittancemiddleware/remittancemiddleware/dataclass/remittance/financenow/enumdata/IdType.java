package com.remittancemiddleware.remittancemiddleware.dataclass.remittance.financenow.enumdata;

public enum IdType {


    COMPANY_REGISTRATION("05"),
    ID_CARD_GOVERNMENT("02"),
    OTHER("99"),
    PASSPORT("01");
    ;

    private String data;

    IdType(String data) {
        this.data = data;
    }

    public String data() {
        return data;
    }

}
