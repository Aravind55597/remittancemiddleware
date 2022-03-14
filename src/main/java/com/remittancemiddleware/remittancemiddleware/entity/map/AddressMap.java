package com.remittancemiddleware.remittancemiddleware.entity.map;




import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;




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

    @OneToOne(mappedBy = "addressMap")
    private PartyMap partyMap;

}
