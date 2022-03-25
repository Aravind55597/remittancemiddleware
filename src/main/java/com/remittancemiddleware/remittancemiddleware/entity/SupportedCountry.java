package com.remittancemiddleware.remittancemiddleware.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.remittancemiddleware.remittancemiddleware.entity.transaction.RemittanceTransaction;
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
@JsonIgnoreProperties({"remittanceCompanies"})
public class SupportedCountry implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //3 letter code name
    @Column(unique=true)
    private String ibanName;

    @ManyToMany(cascade={CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinTable(
            name="supported_country_remittance_company",
            joinColumns=@JoinColumn(name="supported_country_id"),
            inverseJoinColumns=@JoinColumn(name="remittance_company_id")
    )
    private List<RemittanceCompany> remittanceCompanies;

    public void addRemittanceCompanies(RemittanceCompany theRemittanceCompany){
        if(this.remittanceCompanies==null){
            this.remittanceCompanies=new ArrayList<RemittanceCompany>();
        }
        this.remittanceCompanies.add(theRemittanceCompany);
    }


}
