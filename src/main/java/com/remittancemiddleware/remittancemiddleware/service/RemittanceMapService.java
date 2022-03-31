package com.remittancemiddleware.remittancemiddleware.service;

import com.remittancemiddleware.remittancemiddleware.entity.companyfieldmap.RemittanceMap;

import java.util.Map;


public interface RemittanceMapService {
    public RemittanceMap findMapByCountry(int theId, String destCountry);
    public Map getRequiredFields(String destCountry);
    public RemittanceMap save(int theId, String destCountry, Map<String,String> theRemittanceMap);
}
