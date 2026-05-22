package com.hosmalia.gestion_ferme.modules.alimentation.entity;

import java.time.LocalDate;

import com.hosmalia.gestion_ferme.modules.common.AbstractBaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class MouvementStock extends AbstractBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double quantite; // Toujours positif (ex: 500 pour une entrée, 50 pour une sortie)
    private LocalDate dateMouvement;
    private String commentaire; // Ex: "Achat fournisseur X" ou "Fabrication Aliment Lot #3"

    @Enumerated(EnumType.STRING)
    private TypeMouvement type;

    @ManyToOne
    @JoinColumn(name = "matiere_premiere_id")
    private MatierePremiere matierePremiere;
}