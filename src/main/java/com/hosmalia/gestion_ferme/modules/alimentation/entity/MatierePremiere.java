package com.hosmalia.gestion_ferme.modules.alimentation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MatierePremiere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom; // Ex: Maïs, Tourteau de soja
    private Double prixActuelParKg;

    // Getters/Setters
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

    public Double getPrixActuelParKg() {
        return prixActuelParKg;
    }

    public void setPrixActuelParKg(Double prixActuelParKg) {
        this.prixActuelParKg = prixActuelParKg;
    }

}