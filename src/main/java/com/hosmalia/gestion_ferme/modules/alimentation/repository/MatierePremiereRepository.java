package com.hosmalia.gestion_ferme.modules.alimentation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hosmalia.gestion_ferme.modules.alimentation.entity.MatierePremiere;

public interface MatierePremiereRepository extends JpaRepository<MatierePremiere, Long> {
    // Permet d'éviter de créer deux fois le même ingrédient
    Optional<MatierePremiere> findByNomIgnoreCase(String nom);
}
