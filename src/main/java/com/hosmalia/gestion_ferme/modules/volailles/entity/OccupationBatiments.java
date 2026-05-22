package com.hosmalia.gestion_ferme.modules.volailles.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import com.hosmalia.gestion_ferme.modules.common.AbstractBaseEntity;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data // Lombok génère getters, setters, equals, hashCode, toString
@NoArgsConstructor // Constructeur vide (requis par JPA)
@AllArgsConstructor //
public class OccupationBatiments extends AbstractBaseEntity {

    private LocalDate dateEntree;
    private LocalDate dateSortie;

    @ManyToOne
    @JoinColumn(name = "lot_id")
    private LotPoulets lot;

    @ManyToOne
    @JoinColumn(name = "batiment_id")
    private BatimentsPoules batiment;

}