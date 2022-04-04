package com.remittancemiddleware.remittancemiddleware.entity.companyfieldmap;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class AddressMap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String addressLine;

    private String city;

    private String country;

    private String state;

    private String zipCode;

//    @OneToOne(mappedBy = "addressMap")
//    private PartyMap partyMap;

}
