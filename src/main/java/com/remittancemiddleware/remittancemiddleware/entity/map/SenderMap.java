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

    private String senderSourceOfFunds ;


    private String senderBeneficiaryRelationship;

    @OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinColumn(name="ssot_map_id")
    private RemittanceMap remittanceMap;




}
