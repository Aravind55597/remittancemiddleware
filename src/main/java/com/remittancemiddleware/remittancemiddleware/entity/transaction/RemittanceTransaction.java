package com.remittancemiddleware.remittancemiddleware.entity.transaction;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.remittancemiddleware.remittancemiddleware.entity.Company;
import com.remittancemiddleware.remittancemiddleware.entity.enumdata.RemittanceCompanyName;
import com.remittancemiddleware.remittancemiddleware.entity.enumdata.RemittancePurpose;

import com.remittancemiddleware.remittancemiddleware.entity.enumdata.TransactionStatus;
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

    private Long amount; //remittanceAmount enum // nope, remittance amount is just amount //

    private String paymentMode;

    private String sourceType; //sourceType enum // hard code value as it was hard coded from a hidden field

    private String segment; //segment // hard code value, same reason as above

    @OneToOne(cascade = {CascadeType.ALL},orphanRemoval=true)
    @JoinColumn(name = "sender_id")
    private Sender sender;


    @OneToOne(cascade = {CascadeType.ALL},orphanRemoval=true)
    @JoinColumn(name = "receiver_id")
    private Receiver receiver;

    @ManyToOne
    @JoinColumn(name="company_id")
    private Company company;

    //TODO CHANGE THIS TO REMITTANCE COMPANY NAME
    @Enumerated(EnumType.STRING)
    private RemittanceCompanyName remittanceCompany;

    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;

    @JsonBackReference
    public Company getCompany() {
        return company;
    }


}
