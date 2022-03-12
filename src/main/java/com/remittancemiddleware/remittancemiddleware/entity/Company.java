package com.remittancemiddleware.remittancemiddleware.entity;


import com.remittancemiddleware.remittancemiddleware.entity.map.RemittanceMap;
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

    public void addUser(User user){
        if(this.users==null){
            this.users=new ArrayList<User>();
        }
        this.users.add(user);
    }


}
