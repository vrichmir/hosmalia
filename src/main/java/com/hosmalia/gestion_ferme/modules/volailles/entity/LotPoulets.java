package com.hosmalia.gestion_ferme.modules.volailles.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import com.hosmalia.gestion_ferme.modules.common.AbstractBaseEntity;
import com.hosmalia.gestion_ferme.modules.prophylaxie.Entity.Prophylaxie;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data // Lombok génère getters, setters, equals, hashCode, toString
@NoArgsConstructor // Constructeur vide (requis par JPA)
@AllArgsConstructor //
public class LotPoulets extends AbstractBaseEntity {

    @Column(nullable = false, updatable = false)
    private String codeLot;

    private String nom_Lot;
    private LocalDate date_arrivee;
    private Integer effectif_depart;
    private Integer effectif_actuel;

    // La liste des "sous-lots" qui composent ce lot
    @OneToMany(mappedBy = "id")
    private List<LotPoulets> sousLots;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
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

}
