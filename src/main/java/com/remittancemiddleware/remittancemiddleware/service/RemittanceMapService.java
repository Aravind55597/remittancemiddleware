package com.remittancemiddleware.remittancemiddleware.service;

import com.remittancemiddleware.remittancemiddleware.entity.companyfieldmap.RemittanceMap;

import java.util.List;
import java.util.Map;


public interface RemittanceMapService {
    public Map<String,String> findMapByCountry(int theId, String destCountry);
    public List<String> getRequiredFields(String destCountry);
    public List<String> save(int theId, String destCountry, Map<String,String> theRemittanceMap);
    public List<String> update(int theId, String destCountry, Map<String,String> theRemittanceMap);

}
