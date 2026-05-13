package com.hosmalia.gestion_ferme.modules.volailles.entity;

import java.time.LocalDate;

import com.hosmalia.gestion_ferme.modules.prophylaxie.Entity.Produit;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class TraitementsVolailles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateTraitement; // L'information porteuse
    private String observations;

    @ManyToOne
    @JoinColumn(name = "lot_id")
    private LotPoulets lot;

    @ManyToOne
    @JoinColumn(name = "produit_id")
    private Produit produit;

    // Getters/Setters...
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateTraitement() {
        return dateTraitement;
    }

    public void setDateTraitement(LocalDate dateTraitement) {
        this.dateTraitement = dateTraitement;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public LotPoulets getLot() {
        return lot;
    }

    public void setLot(LotPoulets lot) {
        this.lot = lot;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

}
