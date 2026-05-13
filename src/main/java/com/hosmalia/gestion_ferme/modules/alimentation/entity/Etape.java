package com.hosmalia.gestion_ferme.modules.alimentation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Etape {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomEtape;
    private Integer jourDebutEtape;
    private Integer jourFinEtape;

    @Enumerated(EnumType.STRING)
    private TypeAnimal type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomEtape() {
        return nomEtape;
    }

    public void setNomEtape(String nomEtape) {
        this.nomEtape = nomEtape;
    }

    public Integer getJourDebutEtape() {
        return jourDebutEtape;
    }

    public void setJourDebutEtape(Integer jourDebutEtape) {
        this.jourDebutEtape = jourDebutEtape;
    }

    public Integer getJourFinEtape() {
        return jourFinEtape;
    }

    public void setJourFinEtape(Integer jourFinEtape) {
        this.jourFinEtape = jourFinEtape;
    }
}
