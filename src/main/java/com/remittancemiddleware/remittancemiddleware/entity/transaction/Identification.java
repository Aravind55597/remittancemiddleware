package com.remittancemiddleware.remittancemiddleware.entity.transaction;


import com.remittancemiddleware.remittancemiddleware.entity.enumdata.IdType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Identification implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private IdType idType; //enum idType // done

//    @OneToOne(mappedBy = "identification")
//    private Party party;

    private String idNumber;

    private String issuingCountry; //enum issuing country? // nope, using standard country code

    public Identification(IdType idType, String idNumber, String issuingCountry) {
        this.idType = idType;
        this.idNumber = idNumber;
        this.issuingCountry = issuingCountry;
    }
}