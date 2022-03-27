package com.remittancemiddleware.remittancemiddleware.entity.transaction;


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
    protected int id; // change from private to protected for inheritance

    protected String firstName ;

    protected String lastName ;

    protected String nationality;
    // what is this currency for btw? there is payoutCurrency, senderCurrency
    protected String currency; //   Currency enum // Nope don't need, currency has standard notation, just need to ensure all caps

    protected Date dateOfBirth;

    protected String mobileNumber;

    @OneToOne(cascade = CascadeType.ALL,orphanRemoval=true)
    @JoinColumn(name = "identification_id")
    protected Identification identification;

    @OneToOne(cascade = CascadeType.ALL,orphanRemoval=true)
    @JoinColumn(name = "bank_account_id")
    protected BankAccount bankAccount;


    @OneToOne(cascade = CascadeType.ALL,orphanRemoval=true)
    @JoinColumn(name = "address_id")
    protected Address address;



}