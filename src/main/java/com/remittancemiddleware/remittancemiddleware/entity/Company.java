package com.remittancemiddleware.remittancemiddleware.entity;


import com.fasterxml.jackson.annotation.*;
import com.remittancemiddleware.remittancemiddleware.entity.companyfieldmap.RemittanceMap;
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
@JsonIgnoreProperties({"users", "remittanceMaps", "remittanceTransactions"})
public class Company implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String companyName;

    @OneToMany(mappedBy = "company" , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<User> users ;

    @JsonManagedReference
    public List<User> getUsers(){
        return users;
    }

    //__________________________________________________________________________________
    //TODO JSON MANAGED REFERENCE
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "company", fetch = FetchType.LAZY)
    private List<RemittanceMap> remittanceMaps;
   //__________________________________________________________________________________


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "company", fetch = FetchType.LAZY)
    private List<RemittanceTransaction> remittanceTransactions;

    public Company(String companyName) {
        this.companyName = companyName;
    }

    @JsonManagedReference
    public List<RemittanceTransaction> getRemittanceTransactions(){
        return remittanceTransactions;
    }

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

    public void addRemittanceMaps(RemittanceMap theRemittanceMap){
        if(this.remittanceMaps==null){
            this.remittanceMaps=new ArrayList<RemittanceMap>();
        }
        this.remittanceMaps.add(theRemittanceMap);
    }


}
