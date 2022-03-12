package com.remittancemiddleware.remittancemiddleware.entity.map;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
abstract public class PartyMap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName ;

    private String lastName ;

    private String nationality;

    private String currency;

    private String address;

    private String dateOfBirth;

    private String mobileNumber;


    @OneToOne
    @JoinColumn(name = "identification_map_id")
    private IdentificationMap identification;

    @OneToOne
    @JoinColumn(name = "bank_account_map_id")
    private BankAccountMap bankAccountMap;

    @OneToOne
    @JoinColumn(name = "address_map_id")
    private AddressMap addressMap;


}
