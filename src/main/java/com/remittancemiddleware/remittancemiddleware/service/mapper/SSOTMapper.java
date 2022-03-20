package com.remittancemiddleware.remittancemiddleware.service.mapper;

import com.remittancemiddleware.remittancemiddleware.customexception.CustomMappingException;
import com.remittancemiddleware.remittancemiddleware.entity.transaction.RemittanceTransaction;

public interface SSOTMapper<T> {


  T MapSSOT(RemittanceTransaction ssot) throws CustomMappingException;



}
