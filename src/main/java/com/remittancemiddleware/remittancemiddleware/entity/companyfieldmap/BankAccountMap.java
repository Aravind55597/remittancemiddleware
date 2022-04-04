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
public class BankAccountMap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String bankName;

    private String branchName;

    private String accountNumber;


//    @OneToOne(mappedBy = "bankAccountMap")
//    private PartyMap partyMap;


}
