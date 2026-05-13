package com.hosmalia.gestion_ferme.modules.alimentation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Aliment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomAliment;
    private String descriptionAliment;

    @ManyToOne
    @JoinColumn(name = "Etape_id")
    private Etape categorieAnimalConcerne;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomAliment() {
        return nomAliment;
    }

    public void setNomAliment(String nomAliment) {
        this.nomAliment = nomAliment;
    }

    public String getDescriptionAliment() {
        return descriptionAliment;
    }

    public void setDescriptionAliment(String descriptionAliment) {
        this.descriptionAliment = descriptionAliment;
    }

    public Etape getCategorieAnimalConcerne() {
        return categorieAnimalConcerne;
    }

    public void setCategorieAnimalConcerne(Etape categorieAnimalConcerne) {
        this.categorieAnimalConcerne = categorieAnimalConcerne;
    }
}
