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
@Table(name = "receiver")
@PrimaryKeyJoinColumn(name = "party_id")
public class Receiver  extends Party {


    // enum receiverType


    //this should be removed ?

    private int receiverMobileNumber;
}