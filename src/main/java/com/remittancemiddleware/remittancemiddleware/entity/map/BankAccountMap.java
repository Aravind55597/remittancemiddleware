package com.remittancemiddleware.remittancemiddleware.entity.map;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class BankAccountMap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String bankBranchName;

    private String bankAccountNumber;


    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "party_map_id")
    private PartyMap partyMap;



}
