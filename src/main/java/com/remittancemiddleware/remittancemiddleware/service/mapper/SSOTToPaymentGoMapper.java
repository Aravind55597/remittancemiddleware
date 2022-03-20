package com.remittancemiddleware.remittancemiddleware.service.mapper;

import com.remittancemiddleware.remittancemiddleware.dataclass.remittance.paymentgo.PaymentGoData;
import com.remittancemiddleware.remittancemiddleware.entity.enumdata.IdType;
import com.remittancemiddleware.remittancemiddleware.entity.enumdata.RemittancePurpose;
import com.remittancemiddleware.remittancemiddleware.entity.enumdata.SourceOfFunds;

public interface SSOTToPaymentGoMapper extends SSOTMapper<PaymentGoData> {

    public String convertIdType(IdType idType);

    public String convertSourceOfFunds(SourceOfFunds sourceOfFunds);

    public String convertRemittancePurpose(RemittancePurpose remittancePurpose);

}
