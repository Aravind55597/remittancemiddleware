package com.remittancemiddleware.remittancemiddleware.dao;

import com.remittancemiddleware.remittancemiddleware.entity.companyfieldmap.RemittanceMap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RemittanceMapDAO extends JpaRepository<RemittanceMap,Integer> {

}
