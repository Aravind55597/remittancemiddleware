package com.remittancemiddleware.remittancemiddleware.service;

import com.remittancemiddleware.remittancemiddleware.entity.SupportedCountry;

import java.util.List;

public interface SupportedCountryService {
    public List<SupportedCountry> findAll();
}
