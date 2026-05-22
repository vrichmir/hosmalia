package com.hosmalia.gestion_ferme.modules.volailles.entity;

import com.hosmalia.gestion_ferme.modules.common.AbstractBaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data // Lombok génère getters, setters, equals, hashCode, toString
@NoArgsConstructor // Constructeur vide (requis par JPA)
@AllArgsConstructor //
public class BatimentsPoules extends AbstractBaseEntity {

    @Column(nullable = false, updatable = false)
    private String codeBatiment;

    private String nomBatiment;
    private String descriptionBatiment;

}