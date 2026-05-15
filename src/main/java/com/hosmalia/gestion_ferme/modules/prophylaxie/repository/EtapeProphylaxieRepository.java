package com.hosmalia.gestion_ferme.modules.prophylaxie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hosmalia.gestion_ferme.modules.prophylaxie.Entity.EtapeProphylaxie;

import java.util.List;

public interface EtapeProphylaxieRepository extends JpaRepository<EtapeProphylaxie, Long> {

    // Récupère toutes les étapes d'un programme triées par ordre chronologique
    // (jour 1, jour 7...)
    List<EtapeProphylaxie> findByProphylaxieIdOrderByJourRelatifAsc(Long prophylaxieId);

    // Trouve toutes les actions prévues à un jour précis (ex: Jour 42) pour un
    // programme
    List<EtapeProphylaxie> findByProphylaxieIdAndJourRelatif(Long prophylaxieId, Integer jourRelatif);
}