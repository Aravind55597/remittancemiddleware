package com.remittancemiddleware.remittancemiddleware.entity.transaction;

import com.remittancemiddleware.remittancemiddleware.enums.SenderBeneficiaryRelationship;
import com.remittancemiddleware.remittancemiddleware.enums.SourceOfFunds;
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
@PrimaryKeyJoinColumn(name = "party_id")
public class Sender  extends Party{

    public SourceOfFunds sourceOfFunds; //    enum senderSourceOfFunds ; // done


    public SenderBeneficiaryRelationship senderBeneficiaryRelationship; //    enum senderBeneficiaryRelationship; // done

    public Sender(String firstName, String lastName, String nationality, String currency, Date dateOfBirth, int mobileNumber, SourceOfFunds sourceOfFunds, SenderBeneficiaryRelationship senderBeneficiaryRelationship) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationality = nationality;
        this.currency = currency;
        this.dateOfBirth = dateOfBirth;
        this.mobileNumber = mobileNumber;
        this.sourceOfFunds = sourceOfFunds;
        this.senderBeneficiaryRelationship = senderBeneficiaryRelationship;
    }
}