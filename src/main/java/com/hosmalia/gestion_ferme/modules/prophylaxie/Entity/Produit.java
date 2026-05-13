package com.hosmalia.gestion_ferme.modules.prophylaxie.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String descriptionVaccin;
    private String modeAdministration;
    private Integer quantite;

    @Enumerated(EnumType.STRING)
    private TypeProduit typeproduit;

    // Getters/Setters...
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public TypeProduit getTypeproduit() {
        return typeproduit;
    }

    public void setTypeproduit(TypeProduit typeproduit) {
        this.typeproduit = typeproduit;
    }

    public String getDescriptionVaccin() {
        return descriptionVaccin;
    }

    public void setDescriptionVaccin(String descriptionVaccin) {
        this.descriptionVaccin = descriptionVaccin;
    }

    public String getModeAdministration() {
        return modeAdministration;
    }

    public void setModeAdministration(String modeAdministration) {
        this.modeAdministration = modeAdministration;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

}