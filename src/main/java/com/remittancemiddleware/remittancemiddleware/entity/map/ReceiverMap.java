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

    private String receiverType ;

    //this should be removed
    private String receiverMobileNumber;

    @OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinColumn(name="ssot_map_id")
    private RemittanceMap remittanceMap;



}
