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
public class ReceiverMap extends PartyMap {

    //primary key will be id from party

    private String type ;

    private String payoutCurrency;

    @OneToOne(mappedBy = "receiverMap")
    private RemittanceMap remittanceMap;



}
