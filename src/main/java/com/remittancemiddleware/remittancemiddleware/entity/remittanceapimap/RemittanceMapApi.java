package com.remittancemiddleware.remittancemiddleware.entity.remittanceapimap;


import com.remittancemiddleware.remittancemiddleware.entity.RemittanceCompany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class RemittanceMapApi implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String purpose;

    private String amount;

    private String paymentMode;

    private String sourceType;

    private String segment;

    @OneToOne(cascade=CascadeType.ALL ,orphanRemoval=true)
    @JoinColumn(name = "sender_map_api_id")
    private SenderMapApi senderMapApi;


    @OneToOne(cascade=CascadeType.ALL,orphanRemoval=true)
    @JoinColumn(name = "receiver_map_api_id")
    private ReceiverMapApi receiverMapApi;

    @OneToOne(mappedBy= "remittanceMapApi")
    private RemittanceCompany remittanceCompany;

}
