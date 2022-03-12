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

    private String addressCity; //Address city enum? // no enum

    private String addressCountry; //addressCountry enum? // no enum, use standard country code

    private String addressState; //addressState enum? // no enum, use input string

    private int addressZipCode;


    @OneToOne
    @JoinColumn(name = "party_id")
    private Party party;

    public Address(String addressLine, String addressCity, String addressCountry, String addressState, int addressZipCode) {
        this.addressLine = addressLine;
        this.addressCity = addressCity;
        this.addressCountry = addressCountry;
        this.addressState = addressState;
        this.addressZipCode = addressZipCode;
    }
}