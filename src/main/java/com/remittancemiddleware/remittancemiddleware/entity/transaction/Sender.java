package com.remittancemiddleware.remittancemiddleware.entity.transaction;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;



@Entity
@Getter
@Setter
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "party_id")
public class Sender  extends Party{

//    enum senderSourceOfFunds ;


//    enum senderBeneficiaryRelationship;


}