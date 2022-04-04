package com.remittancemiddleware.remittancemiddleware.entity.transaction;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class BankAccount implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String bankName;

    private String branchName;

    private String accountNumber;


//    @OneToOne(mappedBy = "bankAccount")
//    private Party party;

    public BankAccount(String bankName, String branchName, String accountNumber) {
        this.bankName = bankName;
        this.branchName = branchName;
        this.accountNumber = accountNumber;
    }
}