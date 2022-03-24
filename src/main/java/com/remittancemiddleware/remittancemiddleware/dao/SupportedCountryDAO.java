package com.remittancemiddleware.remittancemiddleware.dao;

import com.remittancemiddleware.remittancemiddleware.entity.SupportedCountry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupportedCountryDAO extends JpaRepository<SupportedCountry, Integer> {
}