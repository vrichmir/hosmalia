package com.hosmalia.gestion_ferme.modules.volailles.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class OccupationBatiments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateEntree;
    private LocalDate dateSortie;

    @ManyToOne
    @JoinColumn(name = "lot_id")
    private LotPoulets lot;

    @ManyToOne
    @JoinColumn(name = "batiment_id")
    private BatimentsPoules batiment;

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateEntree() {
        return dateEntree;
    }

    public void setDateEntree(LocalDate dateEntree) {
        this.dateEntree = dateEntree;
    }

    public LocalDate getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(LocalDate dateSortie) {
        this.dateSortie = dateSortie;
    }

    public LotPoulets getLot() {
        return lot;
    }

    public void setLot(LotPoulets lot) {
        this.lot = lot;
    }

    public BatimentsPoules getBatiment() {
        return batiment;
    }

    public void setBatiment(BatimentsPoules batiment) {
        this.batiment = batiment;
    }

}