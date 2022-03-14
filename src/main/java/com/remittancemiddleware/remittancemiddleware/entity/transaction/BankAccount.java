package com.remittancemiddleware.remittancemiddleware.entity.transaction;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class BankAccount  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String bankName;

    private String branchName;

    private int accountNumber;


    @OneToOne(mappedBy = "bankAccount")
    private Party party;

    public BankAccount(String bankName,String branchName, int accountNumber) {
        this.bankName = bankName;
        this.accountNumber = accountNumber;
    }
}