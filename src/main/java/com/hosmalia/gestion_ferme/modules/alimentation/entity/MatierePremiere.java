package com.hosmalia.gestion_ferme.modules.alimentation.entity;

//import java.time.LocalDate;
//import java.util.List;

//import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data // Lombok génère getters, setters, equals, hashCode, toString
@NoArgsConstructor // Constructeur vide (requis par JPA)
@AllArgsConstructor //
public class MatierePremiere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom; // Ex: Maïs, Tourteau de soja
    private Double prixActuelParKg;
    private double proportionMin;
    private double proportionMax;
    private double quantiteEnStock = 0.0;

    /*
     * @OneToMany(mappedBy = "matierePremiere", cascade = CascadeType.ALL)
     * private List<HistoriquePrix> historiquePrix;
     * 
     * 
     * // Method
     * public Double getPrixAEnDate(LocalDate date) {
     * if (historiquePrix == null || historiquePrix.isEmpty()) {
     * return 0.0;
     * }
     * return historiquePrix.stream()
     * // On ne garde que les prix saisis AVANT ou LE JOUR de la date demandée
     * .filter(p -> !p.getDateEffet().isAfter(date))
     * // On trie pour avoir le plus récent en premier
     * .sorted((p1, p2) -> p2.getDateEffet().compareTo(p1.getDateEffet()))
     * .map(HistoriquePrix::getPrixParKg)
     * .findFirst()
     * .orElse(0.0); // Retourne 0 si aucun prix n'existait avant cette date
     * }
     */

}