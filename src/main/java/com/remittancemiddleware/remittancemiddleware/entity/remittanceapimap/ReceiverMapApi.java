package com.remittancemiddleware.remittancemiddleware.entity.remittanceapimap;


import com.remittancemiddleware.remittancemiddleware.entity.companyfieldmap.RemittanceMap;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "party_map_api_id")
public class ReceiverMapApi extends PartyMapApi implements Serializable {

    private String type ;

    private String payoutCurrency;

    private String receiverRemittanceCountry; // added from misplaced receiverCountry previously

    @OneToOne(mappedBy = "receiverMapApi")
    private RemittanceMapApi remittanceMapApi;



}
