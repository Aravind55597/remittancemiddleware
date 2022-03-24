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
public class SenderMapApi extends PartyMapApi implements Serializable {


    private String sourceOfFunds;

    private String senderRemittanceCountry; // added for sender_address_country("SGP") hidden field for EverywhereRemit

    private String senderCurrency ;

    private String beneficiaryRelationship;

    @OneToOne(mappedBy = "senderMapApi")
    private RemittanceMapApi remittanceMapApi;
}
