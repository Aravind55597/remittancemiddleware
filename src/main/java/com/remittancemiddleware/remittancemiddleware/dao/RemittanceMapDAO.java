package com.remittancemiddleware.remittancemiddleware.dao;

import com.remittancemiddleware.remittancemiddleware.entity.Company;
import com.remittancemiddleware.remittancemiddleware.entity.companyfieldmap.RemittanceMap;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RemittanceMapDAO extends JpaRepository<RemittanceMap,Integer> {
    RemittanceMap findByCompanyAndDestinationCountry(Company theCompany, String destinationCountry);
}
