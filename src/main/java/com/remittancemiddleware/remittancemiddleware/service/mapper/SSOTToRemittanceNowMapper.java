package com.remittancemiddleware.remittancemiddleware.service.mapper;

import com.remittancemiddleware.remittancemiddleware.customexception.CustomMappingException;
import com.remittancemiddleware.remittancemiddleware.dataclass.remittance.financenow.FinanceNow;
import com.remittancemiddleware.remittancemiddleware.entity.enumdata.BeneficiaryRelationship;
import com.remittancemiddleware.remittancemiddleware.entity.enumdata.IdType;
import com.remittancemiddleware.remittancemiddleware.entity.enumdata.RemittancePurpose;
import com.remittancemiddleware.remittancemiddleware.entity.enumdata.SourceOfFunds;

public interface SSOTToRemittanceNowMapper extends SSOTMapper<FinanceNow> {


    public String convertCountry(String countrySSOT) throws CustomMappingException;

    public String convertIdType(IdType idType);

    public String convertRemittancePurpose(RemittancePurpose remittancePurpose);

    public String convertBeneficiaryRelationship(BeneficiaryRelationship beneficiaryRelationship);

    public String convertSourceOfFunds(SourceOfFunds sourceOfFunds);
}
