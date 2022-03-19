package com.remittancemiddleware.remittancemiddleware.entity.transaction;

import com.remittancemiddleware.remittancemiddleware.entity.enumdata.BeneficiaryRelationship;
import com.remittancemiddleware.remittancemiddleware.entity.enumdata.SourceOfFunds;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Entity
@Getter
@Setter
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "party_id")
public class Sender  extends Party{

    @Enumerated(EnumType.STRING)
    public SourceOfFunds sourceOfFunds; //    enum senderSourceOfFunds ; // done


    private String senderCurrency; //senderCurency enum // nope should be using standard currency notation i.e. 3 letter string

    @Enumerated(EnumType.STRING)
    public BeneficiaryRelationship beneficiaryRelationship; //    enum senderBeneficiaryRelationship; // done

    public Sender(String firstName, String lastName, String nationality, String currency, Date dateOfBirth, String mobileNumber, SourceOfFunds sourceOfFunds, BeneficiaryRelationship  beneficiaryRelationship) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationality = nationality;
        this.currency = currency;
        this.dateOfBirth = dateOfBirth;
        this.mobileNumber = mobileNumber;
        this.sourceOfFunds = sourceOfFunds;
        this.beneficiaryRelationship = beneficiaryRelationship;
    }
}