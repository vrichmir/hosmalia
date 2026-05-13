package com.hosmalia.gestion_ferme.modules.prophylaxie.Entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class EtapeProphylaxie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer jourRelatif; // Ex: Jour 1, Jour 7, Jour 14
    private String action; // Ex: "Vaccin Newcastle", "Vitamine AD3E"
    private String modeAdministration; // Ex: "Eau de boisson", "Injection"

    @ManyToOne
    private Prophylaxie prophylaxie;

    @OneToMany
    private List<Produit> produit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getJourRelatif() {
        return jourRelatif;
    }

    public void setJourRelatif(Integer jourRelatif) {
        this.jourRelatif = jourRelatif;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getModeAdministration() {
        return modeAdministration;
    }

    public void setModeAdministration(String modeAdministration) {
        this.modeAdministration = modeAdministration;
    }

    public Prophylaxie getProphylaxie() {
        return prophylaxie;
    }

    public void setProphylaxie(Prophylaxie prophylaxie) {
        this.prophylaxie = prophylaxie;
    }

    public List<Produit> getProduit() {
        return produit;
    }

    public void setProduit(List<Produit> produit) {
        this.produit = produit;
    }

}