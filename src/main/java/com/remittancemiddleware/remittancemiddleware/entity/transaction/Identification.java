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

    private int idNumber;

    @OneToOne
    @JoinColumn(name = "party_id")
    private Party party;
    private IdType idType; //enum idType // done

    private String issuingCountry; //enum issuing country? // nope, using standard country code

    public Identification(int idNumber, String idType, String issuingCountry) {
        this.idNumber = idNumber;
        this.idType = IdType.valueOf(idType.toUpperCase());
        this.issuingCountry = issuingCountry;
    }
}