package com.remittancemiddleware.remittancemiddleware.entity.map;



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

    private String sourceOfFunds ;

    private String senderCurrency ;

    private String beneficiaryRelationship;


    @OneToOne(mappedBy = "senderMap")
    private RemittanceMap remittanceMap;

}
