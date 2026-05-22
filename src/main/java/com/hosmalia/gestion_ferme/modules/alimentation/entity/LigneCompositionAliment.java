package com.hosmalia.gestion_ferme.modules.alimentation.entity;

import com.hosmalia.gestion_ferme.modules.common.AbstractBaseEntity;

import jakarta.persistence.Entity;
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
@EqualsAndHashCode(callSuper = true)
@Data // Lombok génère getters, setters, equals, hashCode, toString
@NoArgsConstructor // Constructeur vide (requis par JPA)
@AllArgsConstructor //
public class LigneCompositionAliment extends AbstractBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double proportion;

    @ManyToOne
    @JoinColumn(name = "aliment_id")
    private Aliment aliment;

    @ManyToOne
    @JoinColumn(name = "matiere_premiere_id")
    private MatierePremiere ingredient;

}
