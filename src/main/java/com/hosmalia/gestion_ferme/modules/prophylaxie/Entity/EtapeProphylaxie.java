package com.hosmalia.gestion_ferme.modules.prophylaxie.Entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data // Lombok génère getters, setters, equals, hashCode, toString
@NoArgsConstructor // Constructeur vide (requis par JPA)
@AllArgsConstructor //
public class EtapeProphylaxie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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