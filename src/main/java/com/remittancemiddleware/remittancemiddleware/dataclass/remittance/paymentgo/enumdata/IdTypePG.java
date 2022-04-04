package com.remittancemiddleware.remittancemiddleware.dataclass.remittance.paymentgo.enumdata;

public enum IdTypePG {
    NATIONAL_ID("1"),
    PASSPORT("2");

    private final String data;

    IdTypePG(String data) {
        this.data = data;
    }

    public String data() {
        return data;
    }

}
