package com.remittancemiddleware.remittancemiddleware.entity.transaction;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.Date;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "receiver")
@PrimaryKeyJoinColumn(name = "party_id")
public class Receiver  extends Party {


    private String receiverType; // enum receiverType // nope for enum, hard code default field value "bank_account" from excel


    //this should be removed ?

    private int receiverMobileNumber;


    public Receiver(String firstName, String lastName, String nationality, String currency, Date dateOfBirth, int mobileNumber, String receiverType, int receiverMobileNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationality = nationality;
        this.currency = currency;
        this.dateOfBirth = dateOfBirth;
        this.mobileNumber = mobileNumber;
        this.receiverType = receiverType;
        this.receiverMobileNumber = receiverMobileNumber;
    }
}