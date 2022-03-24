package com.remittancemiddleware.remittancemiddleware.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class SupportedCountry implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //3 letter code name
    @Column(unique=true)
    private String ibanName;


    @ManyToMany
    @JoinTable(
            name="supported_country_remittance_company",
            joinColumns=@JoinColumn(name="supported_country_id"),
            inverseJoinColumns=@JoinColumn(name="remittance_company_id")
    )
    private List<RemittanceCompany> remittanceCompanies;


}
