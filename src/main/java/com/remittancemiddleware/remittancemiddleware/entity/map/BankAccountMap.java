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

    private String branchName;

    private String accountNumber;


    @OneToOne(mappedBy = "bankAccountMap")
    private PartyMap partyMap;



}
