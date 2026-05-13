package com.hosmalia.gestion_ferme.modules.prophylaxie.Entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Prophylaxie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String description;

    @OneToMany(mappedBy = "prophylaxie", cascade = CascadeType.ALL)
    private List<EtapeProphylaxie> etapes;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<EtapeProphylaxie> getEtapes() {
        return etapes;
    }

    public void setEtapes(List<EtapeProphylaxie> etapes) {
        this.etapes = etapes;
    }

}