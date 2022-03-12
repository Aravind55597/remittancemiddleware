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

    //Address city enum?

    //addressCountry enum?

    //addressState enum?

    private int addressZipCode;


    @OneToOne
    @JoinColumn(name = "party_id")
    private Party party;
}