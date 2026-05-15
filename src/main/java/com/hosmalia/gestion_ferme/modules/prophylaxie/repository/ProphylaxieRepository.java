package com.hosmalia.gestion_ferme.modules.prophylaxie.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hosmalia.gestion_ferme.modules.prophylaxie.Entity.Prophylaxie;

public interface ProphylaxieRepository extends JpaRepository<Prophylaxie, Long> {
    // Requête personnalisée automatique pour chercher un programme par son nom
    Optional<Prophylaxie> findByNomContainingIgnoreCase(String nom);
}
