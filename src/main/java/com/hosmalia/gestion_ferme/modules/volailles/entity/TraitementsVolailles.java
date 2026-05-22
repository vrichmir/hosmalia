package com.hosmalia.gestion_ferme.modules.volailles.entity;

import java.time.LocalDate;

import com.hosmalia.gestion_ferme.modules.common.AbstractBaseEntity;
import com.hosmalia.gestion_ferme.modules.prophylaxie.Entity.Produit;

import jakarta.persistence.Entity;

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
public class TraitementsVolailles extends AbstractBaseEntity {

    private LocalDate dateTraitement; // L'information porteuse
    private String observations;

    @ManyToOne
    @JoinColumn(name = "lot_id")
    private LotPoulets lot;

    @ManyToOne
    @JoinColumn(name = "produit_id")
    private Produit produit;

}
