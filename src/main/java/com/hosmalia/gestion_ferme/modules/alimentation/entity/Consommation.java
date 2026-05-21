package com.hosmalia.gestion_ferme.modules.alimentation.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Consommation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateConsommation;
    private double quantiteDistribueeKg;
    private double coutTotalRepas;

    @ManyToOne
    @JoinColumn(name = "aliment_id")
    private Aliment aliment;

    // CORRIGÉ : La consommation est désormais rattachée à une Étape globale
    @ManyToOne
    @JoinColumn(name = "etape_id")
    private Etape etape;
}
