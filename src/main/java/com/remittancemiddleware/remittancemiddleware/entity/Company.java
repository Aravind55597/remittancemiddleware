package com.remittancemiddleware.remittancemiddleware.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.remittancemiddleware.remittancemiddleware.entity.map.RemittanceMap;
import com.remittancemiddleware.remittancemiddleware.entity.transaction.RemittanceTransaction;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Company implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String companyName;

    @OneToMany(mappedBy = "company" , cascade = CascadeType.ALL)
    private List<User> users ;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "remittance_map_id")
    private RemittanceMap remittanceMap;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "company")
    private List<RemittanceTransaction> remittanceTransactions;


    public void addUser(User user){
        if(this.users==null){
            this.users=new ArrayList<User>();
        }
        this.users.add(user);
    }
    public void addRemittanceTransaction(RemittanceTransaction remittanceTransactions){
        if(this.remittanceTransactions==null){
            this.remittanceTransactions=new ArrayList<RemittanceTransaction>();
        }
        this.remittanceTransactions.add(remittanceTransactions);
    }


}
