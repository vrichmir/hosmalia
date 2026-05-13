package com.hosmalia.gestion_ferme.modules.volailles.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.List;

import com.hosmalia.gestion_ferme.modules.prophylaxie.Entity.Prophylaxie;

@Entity
public class LotPoulets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom_Lot;
    private LocalDate date_arrivee;
    private Integer effectif_depart;
    private Integer effectif_actuel;

    // La liste des "sous-lots" qui composent ce lot
    @OneToMany(mappedBy = "lotParent")
    private List<LotPoulets> sousLots;

    @OneToMany(mappedBy = "lot", cascade = CascadeType.ALL)
    private List<Sortie> sorties;

    @ManyToOne
    @JoinColumn(name = "prophylaxie_id")
    private Prophylaxie prophylaxieAppliquee;

    // Méthode pour calculer les poulets restants
    public Integer getStockActuel() {
        Integer totalSorties = sorties.stream()
                .mapToInt(Sortie::getQuantite)
                .sum();
        return this.getEffectif_depart() - totalSorties;
    }

    // Getters et Setters
    public void setEffectif_actuel(Integer effectif_actuel) {
        this.effectif_actuel = effectif_actuel;
    }

    public Integer getEffectif_actuel() {
        return effectif_actuel;
    }

    public void setNom_Lot(String nom_Lot) {
        this.nom_Lot = nom_Lot;
    }

    public void setDate_arrivee(LocalDate date_arrivee) {
        this.date_arrivee = date_arrivee;
    }

    public void setEffectif_depart(Integer effectif_depart) {
        this.effectif_depart = effectif_depart;
    }

    public String getNom_Lot() {
        return nom_Lot;
    }

    public LocalDate getDate_arrivee() {
        return date_arrivee;
    }

    public Integer getEffectif_depart() {
        return effectif_depart;
    }

    public LotPoulets() {
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
