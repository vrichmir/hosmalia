package com.hosmalia.gestion_ferme.modules.alimentation.service;

import com.hosmalia.gestion_ferme.modules.alimentation.dto.ChangementPrixDTO;
import com.hosmalia.gestion_ferme.modules.alimentation.entity.HistoriquePrix;
import com.hosmalia.gestion_ferme.modules.alimentation.entity.MatierePremiere;
import com.hosmalia.gestion_ferme.modules.alimentation.repository.HistoriquePrixRepository;
import com.hosmalia.gestion_ferme.modules.alimentation.repository.MatierePremiereRepository;

import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class HistoriquePrixMatiereService {

    private final MatierePremiereRepository matierePremiereRepository;
    private final HistoriquePrixRepository historiquePrixRepository;

    // Injection par constructeur
    public HistoriquePrixMatiereService(MatierePremiereRepository matierePremiereRepository,
            HistoriquePrixRepository historiquePrixRepository) {
        this.matierePremiereRepository = matierePremiereRepository;
        this.historiquePrixRepository = historiquePrixRepository;
    }

    /**
     * Enregistre un nouveau prix pour une matière première et l'ajoute à son
     * historique de prix.
     */
    @Transactional
    public void modifierPrixMatiere(ChangementPrixDTO dto) {

        // 1. Validation de sécurité sur le prix
        if (dto.getNouveauPrixParKg() <= 0) {
            throw new IllegalArgumentException("Le prix d'une matière première doit être strictement supérieur à 0.");
        }

        // 2. Récupération de la matière première concernée et Fermerture de la ligne
        // active
        MatierePremiere mp = matierePremiereRepository.findById(dto.getMatierePremiereId())
                .orElseThrow(() -> new RuntimeException(
                        "Matière première introuvable avec l'ID: " + dto.getMatierePremiereId()));

        // Si aucune date n'est fournie, on applique le prix à la date du jour
        LocalDate dateApplication = (dto.getDateEffet() != null) ? dto.getDateEffet() : LocalDate.now();

        // Fermerture de l'ancienne ligne active
        Optional<HistoriquePrix> ligneActiveOpt = historiquePrixRepository.findLigneActive(mp.getId(), dateApplication);

        if (ligneActiveOpt.isPresent()) {
            HistoriquePrix ancienPrix = ligneActiveOpt.get();

            // Sécurité : la date du nouveau prix doit être après la date de début de
            // l'ancien
            if (!dateApplication.isAfter(ancienPrix.getDateEffet())) {
                throw new IllegalArgumentException(
                        "La date du nouveau prix doit être postérieure à la date de début de l'ancien prix ("
                                + ancienPrix.getDateEffet() + ").");
            }

            // La date de fin de l'ancien prix devient la veille du nouveau prix
            ancienPrix.setDateFin(dateApplication.minusDays(1));
            historiquePrixRepository.save(ancienPrix); // On ferme l'ancienne période
        }

        // 3. Création de la ligne d'historique
        HistoriquePrix historique = new HistoriquePrix();
        historique.setPrixParKg(dto.getNouveauPrixParKg());
        historique.setDateEffet(dateApplication);
        historique.setMatierePremiere(mp); // Liaison clé étrangère essentielle

        // Sauvegarde de la ligne dans la table historique
        historiquePrixRepository.save(historique);

        // 4. Mise à jour du prix "temps réel" sur la fiche de la matière première
        // Uniquement si la date d'effet est aujourd'hui ou dans le passé
        if (!dateApplication.isAfter(LocalDate.now())) {
            mp.setPrixActuelParKg(dto.getNouveauPrixParKg());
            matierePremiereRepository.save(mp);
        }
    }
}