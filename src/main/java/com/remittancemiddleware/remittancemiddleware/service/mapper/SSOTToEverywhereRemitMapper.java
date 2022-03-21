package com.remittancemiddleware.remittancemiddleware.service.mapper;

import com.remittancemiddleware.remittancemiddleware.customexception.CustomMappingException;
import com.remittancemiddleware.remittancemiddleware.dataclass.remittance.everywhereremit.EverywhereRemitData;
import com.remittancemiddleware.remittancemiddleware.entity.enumdata.IdType;
import com.remittancemiddleware.remittancemiddleware.entity.enumdata.RemittancePurpose;
import com.remittancemiddleware.remittancemiddleware.entity.enumdata.SourceOfFunds;

public interface SSOTToEverywhereRemitMapper extends SSOTMapper<EverywhereRemitData>{

    public String convertCountry(String countrySSOT) throws CustomMappingException;

    public String convertIdType(IdType idType);

    public String convertRemittancePurpose(RemittancePurpose remittancePurpose);

    public String convertSourceOfFunds(SourceOfFunds sourceOfFunds);
}
