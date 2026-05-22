package com.hosmalia.gestion_ferme.modules.prophylaxie.Entity;

import java.util.List;

import com.hosmalia.gestion_ferme.modules.common.AbstractBaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@EqualsAndHashCode(callSuper = true) // <-- IMPORTANT avec Lombok pour intégrer l'id et les dates dans
                                     // equaels/hashCod
@Data // Lombok génère getters, setters, equals, hashCode, toString
@NoArgsConstructor // Constructeur vide (requis par JPA)
@AllArgsConstructor //
public class EtapeProphylaxie extends AbstractBaseEntity {

    @Column(nullable = false, updatable = false)
    private String codeEtapeProphylaxie;

    private Integer jourRelatif; // Ex: Jour 1, Jour 7, Jour 14
    private String action; // Ex: "Vaccin Newcastle", "Vitamine AD3E"
    private String modeAdministration; // Ex: "Eau de boisson", "Injection"

    @ManyToOne
    @JoinColumn(name = "prophylaxie_id")
    private Prophylaxie prophylaxie;

    @ManyToMany
    @JoinTable(name = "etape_prohylaxie_produit", // Nom de la table intermédiaire en base
            joinColumns = @JoinColumn(name = "etape_id"), // Clé étrangère vers l'ID de cette étape
            inverseJoinColumns = @JoinColumn(name = "produit_id") // Clé étrangère vers l'ID du produit
    )
    private List<Produit> listProduit;

}