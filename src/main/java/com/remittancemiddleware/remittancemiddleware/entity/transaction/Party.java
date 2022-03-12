package com.remittancemiddleware.remittancemiddleware.entity.transaction;

import com.remittancemiddleware.remittancemiddleware.entity.map.AddressMap;
import com.remittancemiddleware.remittancemiddleware.entity.map.BankAccountMap;
import com.remittancemiddleware.remittancemiddleware.entity.map.IdentificationMap;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Party  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName ;

    private String lastName ;

    private String nationality;

    //   Currency enum

    private Date dateOfBirth;

    private int mobileNumber;




    @OneToOne
    @JoinColumn(name = "identification_id")
    private Identification identification;

    @OneToOne
    @JoinColumn(name = "bank_account_id")
    private BankAccount bankAccount;


    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;



}