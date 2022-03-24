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
public class BankAccountMapApi implements Serializable {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String bankName;

    private String branchName;

    private String accountNumber;


    @OneToOne(mappedBy = "bankAccountMapApi")
    private PartyMapApi partyMapApi;
}
