package com.remittancemiddleware.remittancemiddleware.dataclass.remittance.everywhereremit.enumdata;

public enum IdTypeRN {


    COMPANY_REGISTRATION("national"),
    ID_CARD_GOVERNMENT("national"),
    OTHER("national"),
    PASSPORT("passport");

    private final String data;

    IdTypeRN(String data) {
        this.data = data;
    }

    public String data() {
        return data;
    }

}
