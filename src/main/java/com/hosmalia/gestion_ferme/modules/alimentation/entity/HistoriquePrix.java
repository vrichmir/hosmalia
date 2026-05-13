package com.hosmalia.gestion_ferme.modules.alimentation.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class HistoriquePrix {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double prixParKg;
    private LocalDate dateEffet; // Date à partir de laquelle ce prix s'applique
    private LocalDate dateFin;

    @ManyToOne
    @JoinColumn(name = "matiere_premiere_id")
    private MatierePremiere matierePremiere;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrixParKg() {
        return prixParKg;
    }

    public void setPrixParKg(Double prixParKg) {
        this.prixParKg = prixParKg;
    }

    public LocalDate getDateEffet() {
        return dateEffet;
    }

    public void setDateEffet(LocalDate dateEffet) {
        this.dateEffet = dateEffet;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public MatierePremiere getMatierePremiere() {
        return matierePremiere;
    }

    public void setMatierePremiere(MatierePremiere matierePremiere) {
        this.matierePremiere = matierePremiere;
    }

    // Getters/Setters
}