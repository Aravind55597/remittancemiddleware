package com.remittancemiddleware.remittancemiddleware.entity.companyfieldmap;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "party_map_id")
public class SenderMap extends PartyMap{

    //primary key will be party Id

    private String sourceOfFunds;

    private String senderRemittanceCountry; // added for sender_address_country("SGP") hidden field for EverywhereRemit

//    private String senderCurrency ;

    private String beneficiaryRelationship;

//    @OneToOne(mappedBy = "senderMap")
//    private RemittanceMap remittanceMap;

}
