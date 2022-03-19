package com.remittancemiddleware.remittancemiddleware.dataclass.remittance.financenow.enumdata;

public enum SenderBeneficiaryRelationship {
    EMPLOYEE("04"),
    FAMILY("01"),
    FRIENDS("02"),
    OTHERS("99"),
    SELF("03"),
    SELLER_SERVICE_PROVIDER("09")
    ;




    private String data;

    SenderBeneficiaryRelationship(String data) {
        this.data = data;
    }

    public String data() {
        return data;
    }

}
