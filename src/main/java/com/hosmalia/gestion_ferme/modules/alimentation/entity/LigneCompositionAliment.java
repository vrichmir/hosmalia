package com.hosmalia.gestion_ferme.modules.alimentation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class LigneCompositionAliment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double proportion;
    private double proportionMin;
    private double proportionMax;

    @ManyToOne
    @JoinColumn(name = "aliment_id")
    private Aliment aliment;

    @ManyToOne
    @JoinColumn(name = "matiere_premiere_id")
    private MatierePremiere ingredient;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getProportion() {
        return proportion;
    }

    public void setProportion(double proportion) {
        this.proportion = proportion;
    }

    public double getProportionMin() {
        return proportionMin;
    }

    public void setProportionMin(double proportionMin) {
        this.proportionMin = proportionMin;
    }

    public double getProportionMax() {
        return proportionMax;
    }

    public void setProportionMax(double proportionMax) {
        this.proportionMax = proportionMax;
    }

    public Aliment getAliment() {
        return aliment;
    }

    public void setAliment(Aliment aliment) {
        this.aliment = aliment;
    }

    public MatierePremiere getIngredient() {
        return ingredient;
    }

    public void setIngredient(MatierePremiere ingredient) {
        this.ingredient = ingredient;
    }

}
