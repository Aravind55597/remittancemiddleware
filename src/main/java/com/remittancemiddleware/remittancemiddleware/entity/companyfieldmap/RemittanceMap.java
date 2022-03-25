package com.remittancemiddleware.remittancemiddleware.entity.companyfieldmap;



import com.fasterxml.jackson.annotation.JsonBackReference;
import com.remittancemiddleware.remittancemiddleware.entity.Company;
import com.remittancemiddleware.remittancemiddleware.entity.remittanceapimap.SenderMapApi;
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

    private Long amount;

    private String paymentMode;

    private String remittanceCompany;

    private String sourceType;

    private String segment;

    @OneToOne(cascade=CascadeType.ALL ,orphanRemoval=true)
    @JoinColumn(name = "sender_map_id")
    private SenderMap senderMap;

    @OneToOne(cascade=CascadeType.ALL,orphanRemoval=true)
    @JoinColumn(name = "receiver_map_id")
    private ReceiverMap receiverMap;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="company_id")
    private Company company;


    private String destinationCountry;


}
