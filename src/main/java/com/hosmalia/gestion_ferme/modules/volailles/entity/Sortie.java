package com.hosmalia.gestion_ferme.modules.volailles.entity;

import java.time.LocalDate;

import com.hosmalia.gestion_ferme.modules.common.AbstractBaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data // Lombok génère getters, setters, equals, hashCode, toString
@NoArgsConstructor // Constructeur vide (requis par JPA)
@AllArgsConstructor //
public class Sortie extends AbstractBaseEntity {

    private LocalDate date;
    private Integer quantite;

    @Enumerated(EnumType.STRING)
    private TypeSortie type; // VENTE ou MORTALITE

    private Double prixVente; // Rempli seulement si type == VENTE
    private String causeDeces; // Rempli seulement si type == MORTALITE

    @ManyToOne
    @JoinColumn(name = "lot_id")
    private LotPoulets lot;

}
