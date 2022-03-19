package com.remittancemiddleware.remittancemiddleware.entity.transaction;



import com.remittancemiddleware.remittancemiddleware.entity.Company;
import com.remittancemiddleware.remittancemiddleware.entity.enums.RemittanceCompany;
import com.remittancemiddleware.remittancemiddleware.entity.enums.RemittancePurpose;

import com.remittancemiddleware.remittancemiddleware.entity.enums.TransactionStatus;
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

    @OneToOne(cascade = {CascadeType.ALL},orphanRemoval=true)
    @JoinColumn(name = "receiver_ID")
    private Receiver receiver;

    @ManyToOne
    @JoinColumn(name="company_ID")
    private Company company;

}
