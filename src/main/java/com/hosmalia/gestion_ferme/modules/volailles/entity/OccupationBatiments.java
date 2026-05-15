package com.hosmalia.gestion_ferme.modules.volailles.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data // Lombok génère getters, setters, equals, hashCode, toString
@NoArgsConstructor // Constructeur vide (requis par JPA)
@AllArgsConstructor //
public class OccupationBatiments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateEntree;
    private LocalDate dateSortie;

    @ManyToOne
    @JoinColumn(name = "lot_id")
    private LotPoulets lot;

    @ManyToOne
    @JoinColumn(name = "batiment_id")
    private BatimentsPoules batiment;

}