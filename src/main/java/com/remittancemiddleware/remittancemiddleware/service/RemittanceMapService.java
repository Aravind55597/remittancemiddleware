package com.remittancemiddleware.remittancemiddleware.service;

import java.util.List;
import java.util.Map;


public interface RemittanceMapService {
    Map<String, String> findMapByCountry(int theId, String destCountry);

    List<String> getRequiredFields(String destCountry);

    List<String> save(int theId, String destCountry, Map<String, String> theRemittanceMap);

    List<String> update(int theId, String destCountry, Map<String, String> theRemittanceMap);

}
