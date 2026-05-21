package com.hosmalia.gestion_ferme.modules.alimentation.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data // Lombok génère getters, setters, equals, hashCode, toString
@NoArgsConstructor // Constructeur vide (requis par JPA)
@AllArgsConstructor //
public class Aliment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomAliment;
    private String descriptionAliment;
    private Integer version = 1;
    private boolean actif = true;
    private double quantiteEnStock = 0.0;

    @ManyToOne
    @JoinColumn(name = "categorieAnimalConcerne")
    private Etape categorieAnimalConcerne;

    @OneToMany(mappedBy = "aliment", cascade = CascadeType.ALL, orphanRemoval = true)
    // INITIALISATION DIRECTE : Plus besoin de faire de "set" dans le service !
    private List<LigneCompositionAliment> lignesComposition = new ArrayList<>();

    public void ajouterLigne(LigneCompositionAliment ligne) {
        if (this.lignesComposition == null) {
            this.lignesComposition = new ArrayList<>();
        }
        this.lignesComposition.add(ligne);
        ligne.setAliment(this); // Crucial : on dit à la ligne qu'elle appartient à cet aliment
    }

}
