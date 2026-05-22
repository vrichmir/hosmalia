package com.hosmalia.gestion_ferme.modules.alimentation.entity;

import com.hosmalia.gestion_ferme.modules.common.AbstractBaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data // Lombok génère getters, setters, equals, hashCode, toString
@NoArgsConstructor // Constructeur vide (requis par JPA)
@AllArgsConstructor //
public class Etape extends AbstractBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomEtape;
    private Integer jourDebutEtape;
    private Integer jourFinEtape;

    @Enumerated(EnumType.STRING)
    private TypeAnimal type;

}
