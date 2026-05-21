package com.hosmalia.gestion_ferme.modules.alimentation.service;

import com.hosmalia.gestion_ferme.modules.alimentation.entity.*;
import com.hosmalia.gestion_ferme.modules.alimentation.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;

@Service
public class ConsommationService {

    private final ConsommationRepository consommationRepository;
    private final AlimentRepository alimentRepository;
    private final EtapeRepository etapeRepository; // Injecté à la place de LotPouletsRepository
    private final StockService stockService;
    private final HistoriquePrixRepository historiquePrixRepository;

    public ConsommationService(ConsommationRepository consommationRepository,
            AlimentRepository alimentRepository,
            EtapeRepository etapeRepository,
            StockService stockService,
            HistoriquePrixRepository historiquePrixRepository) {
        this.consommationRepository = consommationRepository;
        this.alimentRepository = alimentRepository;
        this.etapeRepository = etapeRepository;
        this.stockService = stockService;
        this.historiquePrixRepository = historiquePrixRepository;
    }

    /**
     * Enregistre la consommation d'une étape d'élevage, calcule son coût et déduit
     * les stocks.
     */
    @Transactional
    public Consommation distribuerAliment(Long etapeId, Long alimentId, double quantiteKg) {

        // 1. Récupération et vérifications
        Etape etape = etapeRepository.findById(etapeId)
                .orElseThrow(() -> new RuntimeException("Étape d'élevage introuvable ID : " + etapeId));

        Aliment aliment = alimentRepository.findById(alimentId)
                .orElseThrow(() -> new RuntimeException("Aliment introuvable ID : " + alimentId));

        if (!aliment.isActif()) {
            throw new IllegalArgumentException("Impossible d'utiliser une formule d'aliment inactive.");
        }

        LocalDate aujourdhui = LocalDate.now();

        // 2. Déduction du stock avec libellé adapté à l'étape
        stockService.consommerPourFabrication(aliment, quantiteKg, "Étape : " + etape.getNomEtape());

        // 3. Calcul du coût financier du repas
        double coutParKg = aliment.getLignesComposition().stream()
                .mapToDouble(ligne -> {
                    Double prixEpoque = historiquePrixRepository
                            .findLigneActive(ligne.getIngredient().getId(), aujourdhui)
                            .map(HistoriquePrix::getPrixParKg)
                            .orElse(ligne.getIngredient().getPrixActuelParKg());
                    return (ligne.getProportion() * prixEpoque) / 100.0;
                }).sum();

        double coutTotal = coutParKg * quantiteKg;

        // 4. Enregistrement de la consommation
        Consommation fiche = new Consommation();
        fiche.setDateConsommation(aujourdhui);
        fiche.setQuantiteDistribueeKg(quantiteKg);
        fiche.setCoutTotalRepas(coutTotal);
        fiche.setAliment(aliment);
        fiche.setEtape(etape); // Associé à l'étape

        return consommationRepository.save(fiche);
    }
}
