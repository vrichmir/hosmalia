package com.hosmalia.gestion_ferme.modules.alimentation.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

import com.hosmalia.gestion_ferme.modules.common.AbstractBaseEntity;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Consommation extends AbstractBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateConsommation;
    private double quantiteDistribueeKg;
    private double coutTotalRepas;

    @ManyToOne
    @JoinColumn(name = "aliment_id")
    private Aliment aliment;

    // CORRIGÉ : La consommation est rattachée à une Étape globale
    @ManyToOne
    @JoinColumn(name = "etape_id")
    private Etape etape;
}
