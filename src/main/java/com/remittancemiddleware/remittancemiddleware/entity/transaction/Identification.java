package com.remittancemiddleware.remittancemiddleware.entity.transaction;

import com.remittancemiddleware.remittancemiddleware.enums.IdType;
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

    private String idNumber;

    @OneToOne(mappedBy = "identification")
    private Party party;

    @Enumerated(EnumType.STRING)
    private IdType idType; //enum idType // done

    private String issuingCountry; //enum issuing country? // nope, using standard country code

    public Identification(String idNumber, IdType idType, String issuingCountry) {
        this.idNumber = idNumber;
        this.idType = idType;
        this.issuingCountry = issuingCountry;
    }
}