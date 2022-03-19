package com.remittancemiddleware.remittancemiddleware.entity.transaction;



import com.fasterxml.jackson.annotation.JsonBackReference;
import com.remittancemiddleware.remittancemiddleware.entity.Company;
import com.remittancemiddleware.remittancemiddleware.entity.map.SenderMap;
import com.remittancemiddleware.remittancemiddleware.enums.RemittanceCompany;
import com.remittancemiddleware.remittancemiddleware.enums.RemittancePurpose;

import com.remittancemiddleware.remittancemiddleware.enums.TransactionStatus;
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

    @Enumerated(EnumType.STRING)
    private RemittancePurpose purpose; //remittancePurpose enum // done

    private long amount; //remittanceAmount enum // nope, remittance amount is just amount

    @Enumerated(EnumType.STRING)
    private RemittanceCompany remittanceCompany;

    private String sourceType; //sourceType enum // hard code value as it was hard coded from a hidden field

    private String segment; //segment // hard code value, same reason as above

    private String paymentMode;

    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;

    @OneToOne(cascade = {CascadeType.ALL},orphanRemoval=true)
    @JoinColumn(name = "sender_ID")
    private Sender sender;

    @JsonBackReference
    public Sender getSender(){
        return sender;
    }

    @OneToOne(cascade = {CascadeType.ALL},orphanRemoval=true)
    @JoinColumn(name = "receiver_ID")
    private Receiver receiver;

    @JsonBackReference
    public Receiver getReceiver(){
        return receiver;
    }

    @ManyToOne
    @JoinColumn(name="company_ID")
    private Company company;

    @JsonBackReference
    public Company getCompany() {
        return company;
    }


}
