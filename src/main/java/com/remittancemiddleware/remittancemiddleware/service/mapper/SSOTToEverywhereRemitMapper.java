package com.remittancemiddleware.remittancemiddleware.service.mapper;

import com.remittancemiddleware.remittancemiddleware.customexception.CustomMappingException;
import com.remittancemiddleware.remittancemiddleware.dataclass.remittance.everywhereremit.EverywhereRemitData;
import com.remittancemiddleware.remittancemiddleware.entity.enumdata.IdType;
import com.remittancemiddleware.remittancemiddleware.entity.enumdata.RemittancePurpose;
import com.remittancemiddleware.remittancemiddleware.entity.enumdata.SourceOfFunds;

public interface SSOTToEverywhereRemitMapper extends SSOTMapper<EverywhereRemitData> {

    String convertCountry(String countrySSOT) throws CustomMappingException;

    String convertIdType(IdType idType);

    String convertRemittancePurpose(RemittancePurpose remittancePurpose);

    String convertSourceOfFunds(SourceOfFunds sourceOfFunds);
}
