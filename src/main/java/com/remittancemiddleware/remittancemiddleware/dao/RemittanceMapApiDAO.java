package com.remittancemiddleware.remittancemiddleware.dao;

import com.remittancemiddleware.remittancemiddleware.entity.remittanceapimap.RemittanceMapApi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RemittanceMapApiDAO extends JpaRepository<RemittanceMapApi, Integer> {
}