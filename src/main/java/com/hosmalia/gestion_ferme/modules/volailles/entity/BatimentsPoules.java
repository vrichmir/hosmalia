package com.hosmalia.gestion_ferme.modules.volailles.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class BatimentsPoules {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomBatiment;
    private String descriptionBatiment;

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public String getNomBatiment() {
        return nomBatiment;
    }

    public String getDescriptionBatiment() {
        return descriptionBatiment;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNomBatiment(String nomBatiment) {
        this.nomBatiment = nomBatiment;
    }

    public void setDescriptionBatiment(String descriptionBatiment) {
        this.descriptionBatiment = descriptionBatiment;
    }

}