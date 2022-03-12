package com.remittancemiddleware.remittancemiddleware.entity.transaction;



import com.remittancemiddleware.remittancemiddleware.entity.map.SenderMap;
import com.remittancemiddleware.remittancemiddleware.enums.RemittancePurpose;

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


    private RemittancePurpose remittancePurpose; //remittancePurpose enum // done

    private long remittanceAmount; //remittanceAmount enum // nope, remittance amount is just amount

    private String senderCurrency; //senderCurency enum // nope should be using standard currency notation i.e. 3 letter string


    private String payoutCurrency; //payoutCurrency enum // no enum, same as above

    private String sourceType; //sourceType enum // hard code value as it was hard coded from a hidden field

    private String segment; //segment // hard code value, same reason as above

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "sender_ID")
    private Sender sender;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "receiver_ID")
    private Receiver receiver;

}