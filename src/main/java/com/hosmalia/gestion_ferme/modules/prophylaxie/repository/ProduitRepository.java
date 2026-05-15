package com.hosmalia.gestion_ferme.modules.prophylaxie.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hosmalia.gestion_ferme.modules.prophylaxie.Entity.Produit;

public interface ProduitRepository extends JpaRepository<Produit, Long> {
    Optional<Produit> findByNomContainingIgnoreCase(String nom);
}
