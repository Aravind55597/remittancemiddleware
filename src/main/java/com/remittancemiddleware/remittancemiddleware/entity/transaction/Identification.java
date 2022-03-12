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
public class Identification  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int idNumber;

    @OneToOne
    @JoinColumn(name = "party_id")
    private Party party;
    //enum idType

    //enum issuing country?
}