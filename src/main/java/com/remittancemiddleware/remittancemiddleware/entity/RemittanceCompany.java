package com.remittancemiddleware.remittancemiddleware.entity;


import com.remittancemiddleware.remittancemiddleware.entity.enumdata.RemittanceCompanyName;
import com.remittancemiddleware.remittancemiddleware.entity.remittanceapimap.RemittanceMapApi;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class RemittanceCompany implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique=true)
    private RemittanceCompanyName remittanceCompanyName;


    @ManyToMany(cascade={CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinTable(
            name="supported_country_remittance_company",
            joinColumns=@JoinColumn(name="remittance_company_id"),
            inverseJoinColumns=@JoinColumn(name="supported_country_id")
    )
    private List<SupportedCountry> supportedCountries;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="remittance_api_map")
    private RemittanceMapApi remittanceMapApi;

    public void addSupportedCountries(SupportedCountry theSupportedCountry){
        if(this.supportedCountries==null){
            this.supportedCountries=new ArrayList<SupportedCountry>();
        }
        this.supportedCountries.add(theSupportedCountry);
    }





}
