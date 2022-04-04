package com.remittancemiddleware.remittancemiddleware.service.mapper;

import com.remittancemiddleware.remittancemiddleware.customexception.CustomMappingException;
import com.remittancemiddleware.remittancemiddleware.dataclass.remittance.financenow.FinanceNowData;
import com.remittancemiddleware.remittancemiddleware.entity.enumdata.BeneficiaryRelationship;
import com.remittancemiddleware.remittancemiddleware.entity.enumdata.IdType;
import com.remittancemiddleware.remittancemiddleware.entity.enumdata.RemittancePurpose;
import com.remittancemiddleware.remittancemiddleware.entity.enumdata.SourceOfFunds;

public interface SSOTToFinanceNowMapper extends SSOTMapper<FinanceNowData> {


    String convertCountry(String countrySSOT) throws CustomMappingException;

    String convertIdType(IdType idType);

    String convertRemittancePurpose(RemittancePurpose remittancePurpose);

    String convertBeneficiaryRelationship(BeneficiaryRelationship beneficiaryRelationship);

    String convertSourceOfFunds(SourceOfFunds sourceOfFunds);
}
