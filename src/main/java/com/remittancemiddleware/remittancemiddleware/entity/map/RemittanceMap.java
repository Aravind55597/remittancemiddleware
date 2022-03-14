package com.remittancemiddleware.remittancemiddleware.entity.map;



import com.remittancemiddleware.remittancemiddleware.entity.Company;
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

    private String purpose;

    private String amount;


    private String paymentMode;

    private String sourceType;

    private String segment;

    @OneToOne(cascade=CascadeType.ALL ,orphanRemoval=true)
    @JoinColumn(name = "sender_map_id")
    private SenderMap senderMap;

    @OneToOne(cascade=CascadeType.ALL,orphanRemoval=true)
    @JoinColumn(name = "receiver_map_id")
    private ReceiverMap receiverMap;

    @OneToOne(mappedBy = "remittanceMap")
    private Company company ;


}
