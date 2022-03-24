package com.remittancemiddleware.remittancemiddleware.entity.remittanceapimap;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class AddressMapApi implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String addressLine;

    private String city;

    private String country;

    private String state;

    private String zipCode;

//    @OneToOne(mappedBy = "addressMapApi")
//    private PartyMapApi partyMapApi;


}
