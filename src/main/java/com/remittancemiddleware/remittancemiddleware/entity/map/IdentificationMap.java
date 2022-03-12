package com.remittancemiddleware.remittancemiddleware.entity.map;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;




@Entity
@Getter
@Setter
@NoArgsConstructor
public class IdentificationMap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String idType;

    private String issuingCountry;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "party_map_id")
    private PartyMap partyMap;

}
