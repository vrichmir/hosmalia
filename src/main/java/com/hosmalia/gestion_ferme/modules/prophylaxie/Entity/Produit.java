package com.hosmalia.gestion_ferme.modules.prophylaxie.Entity;

import com.hosmalia.gestion_ferme.modules.common.AbstractBaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data // Lombok génère getters, setters, equals, hashCode, toString
@EqualsAndHashCode(callSuper = true) // <-- IMPORTANT avec Lombok pour intégrer l'id et les dates dans
                                     // equals/hashCode
@NoArgsConstructor // Constructeur vide (requis par JPA)
@AllArgsConstructor //
public class Produit extends AbstractBaseEntity {

    private String nom;
    private String descriptionVaccin;
    private String modeAdministration;
    private Integer quantite;

    @Enumerated(EnumType.STRING)
    private TypeProduit typeproduit;

}