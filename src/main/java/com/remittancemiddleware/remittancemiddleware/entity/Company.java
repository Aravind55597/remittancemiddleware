package com.remittancemiddleware.remittancemiddleware.entity;


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

    @OneToMany(mappedBy = "company")
    private List<User> users ;

    private RemittanceMap remittanceMap;

    @OneToMany(mappedBy = "company") // not sure if this is correct, but i added for you, pls check - clarence
    private List<RemittanceTransaction> remittanceTransactions;

    public Company(String companyName) {
        this.companyName = companyName;
    }

    public void addUser(User user){
        if(this.users==null){
            this.users=new ArrayList<User>();
        }
        this.users.add(user);
    }


}
