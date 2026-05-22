package com.hosmalia.gestion_ferme.modules.prophylaxie.Entity;

import com.hosmalia.gestion_ferme.modules.common.AbstractBaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@EqualsAndHashCode(callSuper = true) // <-- IMPORTANT avec Lombok pour intégrer l'id et les dates dans
                                     // equals/hashCode
@Data // Lombok génère getters, setters, equals, hashCode, toString
@NoArgsConstructor // Constructeur vide (requis par JPA)
@AllArgsConstructor //
public class Prophylaxie extends AbstractBaseEntity {

    @Column(nullable = false, updatable = false)
    private String codeProphylaxie;

    private String nomProphylaxie;
    private String descriptionProphylaxie;

    /*
     * @OneToMany(mappedBy = "prophylaxie", cascade = CascadeType.ALL)
     * private List<EtapeProphylaxie> etapes;
     */

}