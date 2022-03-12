package com.remittancemiddleware.remittancemiddleware.entity.map;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class RemittanceMap implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String remittancePurpose;

    private String remittanceAmount;

    private String senderCurrency ;

    private String paymentMode;

//    @OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
//    @JoinColumn(name="party_map_id")
    @OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinColumn(name = "sender_map_id")
    private SenderMap senderMap;

//    @OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
//    @JoinColumn(name="party_map_id")
    @OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinColumn(name = "receiver_map_id")
    private ReceiverMap receiverMap;


}
