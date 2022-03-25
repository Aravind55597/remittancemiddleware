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
public class Address  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String addressLine;

    private String city; //Address city enum? // no enum

    private String country; //addressCountry enum? // no enum, use standard country code

    private String state; //addressState enum? // no enum, use input string

    private int zipCode;


//    @OneToOne
//    @JoinColumn(name = "party_id")
//    private Party party;

    public Address(String addressLine, String city, String country, String state, int zipCode) {
        this.addressLine = addressLine;
        this.city = city;
        this.country = country;
        this.state = state;
        this.zipCode = zipCode;
    }
}