package com.hosmalia.gestion_ferme.modules.alimentation.service;

import com.hosmalia.gestion_ferme.modules.alimentation.entity.*;
import com.hosmalia.gestion_ferme.modules.alimentation.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;

@Service
public class StockService {

    private final MatierePremiereRepository matierePremiereRepository;
    private final MouvementStockRepository mouvementRepository;

    public StockService(MatierePremiereRepository matierePremiereRepository,
            MouvementStockRepository mouvementRepository) {
        this.matierePremiereRepository = matierePremiereRepository;
        this.mouvementRepository = mouvementRepository;
    }

    /**
     * Approvisionnement : Enregistre un achat et augmente le stock physique
     */
    @Transactional
    public void ajouterStock(Long matiereId, double quantiteAchetee, String fournisseur) {
        MatierePremiere mp = matierePremiereRepository.findById(matiereId)
                .orElseThrow(() -> new RuntimeException("Matière première introuvable"));

        // Mouvement comptable
        MouvementStock mouvement = new MouvementStock();
        mouvement.setQuantite(quantiteAchetee);
        mouvement.setType(TypeMouvement.ENTREE_ACHAT);
        mouvement.setDateMouvement(LocalDate.now());
        mouvement.setCommentaire("Achat - Fournisseur: " + fournisseur);
        mouvement.setMatierePremiere(mp);
        mouvementRepository.save(mouvement);

        // Mise à jour du stock physique
        mp.setQuantiteEnStock(mp.getQuantiteEnStock() + quantiteAchetee);
        matierePremiereRepository.save(mp);
    }

    /**
     * Consommation : Déduit les matières premières proportionnellement à la
     * quantité d'aliment fabriquée
     */
    @Transactional
    public void consommerPourFabrication(Aliment aliment, double quantiteAlimentEnKg, String lotDestinataire) {

        // 1. PRE-VERIFICATION : S'assurer que TOUT est disponible avant de commencer à
        // déduire
        for (LigneCompositionAliment ligne : aliment.getLignesComposition()) {
            MatierePremiere mp = ligne.getIngredient();
            // Calcul du besoin : (Proportion / 100) * Quantité totale à fabriquer
            double besoinKg = (ligne.getProportion() / 100.0) * quantiteAlimentEnKg;

            if (mp.getQuantiteEnStock() < besoinKg) {
                throw new IllegalStateException("Stock insuffisant pour " + mp.getNom() +
                        " : Besoin de " + besoinKg + "kg mais seulement " + mp.getQuantiteEnStock() + "kg en stock.");
            }
        }

        // 2. DEBIT DES STOCKS : Si tout est disponible, on applique les sorties
        for (LigneCompositionAliment ligne : aliment.getLignesComposition()) {
            MatierePremiere mp = ligne.getIngredient();
            double besoinKg = (ligne.getProportion() / 100.0) * quantiteAlimentEnKg;

            // Enregistrer la sortie
            MouvementStock mouvement = new MouvementStock();
            mouvement.setQuantite(besoinKg);
            mouvement.setType(TypeMouvement.SORTIE_FABRICATION);
            mouvement.setDateMouvement(LocalDate.now());
            mouvement.setCommentaire("Distribution " + aliment.getNomAliment() + " - " + lotDestinataire);
            mouvement.setMatierePremiere(mp);
            mouvementRepository.save(mouvement);

            // Soustraction du stock physique
            mp.setQuantiteEnStock(mp.getQuantiteEnStock() - besoinKg);
            matierePremiereRepository.save(mp);
        }
    }
}
