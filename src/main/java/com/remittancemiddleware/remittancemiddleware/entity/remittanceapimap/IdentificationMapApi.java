package com.remittancemiddleware.remittancemiddleware.entity.remittanceapimap;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class IdentificationMapApi implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String idType;

    private String idNumber;

    private String issuingCountry;

//    @OneToOne(mappedBy = "identificationMapApi")
//    private PartyMapApi partyMapApi;
}
