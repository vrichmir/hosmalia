package com.hosmalia.gestion_ferme.modules.volailles.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Sortie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private Integer quantite;

    @Enumerated(EnumType.STRING)
    private TypeSortie type; // VENTE ou MORTALITE

    private Double prixVente; // Rempli seulement si type == VENTE
    private String causeDeces; // Rempli seulement si type == MORTALITE

    @ManyToOne
    @JoinColumn(name = "lot_id")
    private LotPoulets lot;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public TypeSortie getType() {
        return type;
    }

    public void setType(TypeSortie type) {
        this.type = type;
    }

    public Double getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(Double prixVente) {
        this.prixVente = prixVente;
    }

    public String getCauseDeces() {
        return causeDeces;
    }

    public void setCauseDeces(String causeDeces) {
        this.causeDeces = causeDeces;
    }

    public LotPoulets getLot() {
        return lot;
    }

    public void setLot(LotPoulets lot) {
        this.lot = lot;
    }
}
