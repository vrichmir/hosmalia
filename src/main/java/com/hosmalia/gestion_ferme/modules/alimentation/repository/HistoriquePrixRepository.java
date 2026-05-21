package com.hosmalia.gestion_ferme.modules.alimentation.repository;

import com.hosmalia.gestion_ferme.modules.alimentation.entity.HistoriquePrix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.Optional;

public interface HistoriquePrixRepository extends JpaRepository<HistoriquePrix, Long> {

    // Requête SQL/HQL pour trouver le prix le plus récent à une date précise
    @Query("SELECT h.prixParKg FROM HistoriquePrix h " +
            "WHERE h.matierePremiere.id = :matiereId AND :datej >= h.dateEffet  and (h.dateFin>=:datej or h.dateFin is null)"
            +
            "ORDER BY h.dateEffet DESC LIMIT 1")
    Optional<Double> findPrixEnCours(@Param("matiereId") Long matiereId, @Param("datedebut") LocalDate datej);

    @Query("SELECT h.prixParKg FROM HistoriquePrix h " +
            "WHERE h.matierePremiere.id = :matiereId AND :date between h.dateEffet and h.dateFin  " +
            "ORDER BY h.dateEffet DESC LIMIT 1")
    Optional<Double> findPrixADate(@Param("matiereId") Long matiereId, @Param("datedebut") LocalDate date);

    @Query("SELECT h FROM HistoriquePrix h "
            + "WHERE h.matierePremiere.id = :matiereId "
            + "AND :datej >= h.dateEffet "
            + "AND (h.dateFin >= :datej OR h.dateFin IS NULL)")
    Optional<HistoriquePrix> findLigneActive(@Param("matiereId") Long matiereId, @Param("datej") LocalDate datej);

}
