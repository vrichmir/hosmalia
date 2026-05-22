package com.hosmalia.gestion_ferme.modules.alimentation.entity;

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
public class HistoriquePrix extends AbstractBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double prixParKg;
    private LocalDate dateEffet; // Date à partir de laquelle ce prix s'applique
    private LocalDate dateFin;

    @ManyToOne
    @JoinColumn(name = "matiere_premiere_id")
    private MatierePremiere matierePremiere;

}