package com.remittancemiddleware.remittancemiddleware.entity.transaction;



import com.remittancemiddleware.remittancemiddleware.entity.map.SenderMap;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class RemittanceTransaction  implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    //remittancePurpose enum


    //remittanceAmount enum

    //senderCurency enum


    //payoutCurrency enum

    //sourceType enum

    //segment

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "sender_ID")
    private Sender sender;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "receiver_ID")
    private Receiver receiver;

}
