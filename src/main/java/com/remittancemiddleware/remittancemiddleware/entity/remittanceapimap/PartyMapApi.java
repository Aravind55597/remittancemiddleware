package com.remittancemiddleware.remittancemiddleware.entity.remittanceapimap;


import com.remittancemiddleware.remittancemiddleware.entity.companyfieldmap.AddressMap;
import com.remittancemiddleware.remittancemiddleware.entity.companyfieldmap.BankAccountMap;
import com.remittancemiddleware.remittancemiddleware.entity.companyfieldmap.IdentificationMap;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
abstract public class PartyMapApi implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName ;

    private String lastName ;

    private String nationality;

    private String currency;


    private String dateOfBirth;

    private String mobileNumber;


    @OneToOne(cascade = CascadeType.ALL,orphanRemoval=true)
    @JoinColumn(name = "identification_map_api_id")
    private IdentificationMapApi identificationMapApi;

    @OneToOne(cascade = CascadeType.ALL,orphanRemoval=true)
    @JoinColumn(name = "bank_account_map_api_id")
    private BankAccountMapApi bankAccountMapApi;

    @OneToOne(cascade = CascadeType.ALL,orphanRemoval=true)
    @JoinColumn(name = "address_map_api_id")
    private AddressMapApi addressMapApi;

}
