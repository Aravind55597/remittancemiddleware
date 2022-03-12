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


    private String addressCity;

    private String addressCountry;


    private String addressState;

    private String addressZipCode;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "party_map_id")
    private PartyMap partyMap;


}
