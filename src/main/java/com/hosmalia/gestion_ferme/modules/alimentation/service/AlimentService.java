package com.hosmalia.gestion_ferme.modules.alimentation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hosmalia.gestion_ferme.modules.alimentation.dto.LigneSaisieMatiereDTO;
import com.hosmalia.gestion_ferme.modules.alimentation.entity.Aliment;
import com.hosmalia.gestion_ferme.modules.alimentation.entity.Etape;
import com.hosmalia.gestion_ferme.modules.alimentation.entity.LigneCompositionAliment;
import com.hosmalia.gestion_ferme.modules.alimentation.entity.MatierePremiere;
import com.hosmalia.gestion_ferme.modules.alimentation.repository.AlimentRepository;
import com.hosmalia.gestion_ferme.modules.alimentation.repository.EtapeRepository;
import com.hosmalia.gestion_ferme.modules.alimentation.repository.LigneCompositionAlimentRepository;
import com.hosmalia.gestion_ferme.modules.alimentation.repository.MatierePremiereRepository;

import jakarta.transaction.Transactional;

@Service
public class AlimentService {

    private final AlimentRepository alimentRepository;
    private final MatierePremiereRepository matierePremiereRepository;
    private final EtapeRepository etapeRepository; // Si vous avez un repository pour Etape

    public AlimentService(AlimentRepository alimentRepository,
            MatierePremiereRepository matierePremiereRepository,
            EtapeRepository etapeRepository,
            LigneCompositionAlimentRepository ligneCompositionAlimentRepository) {
        this.alimentRepository = alimentRepository;
        this.matierePremiereRepository = matierePremiereRepository;
        this.etapeRepository = etapeRepository;

    }

    @Transactional
    public Aliment creerAliment(String nom, String description, Long etapeId,
            List<LigneSaisieMatiereDTO> ingredientsSaisis) {

        // 1. Validation de la somme des proportions (doit faire 100%)
        double totalProportion = ingredientsSaisis.stream().mapToDouble(i -> i.proportion).sum();
        if (Math.abs(totalProportion - 100.0) > 0.01) {
            throw new IllegalArgumentException(
                    "La somme des proportions doit être de 100%. Actuel: " + totalProportion + "%");
        }

        // 2. Initialisation de l'Aliment
        Aliment nouvelAliment = new Aliment();
        nouvelAliment.setNomAliment(nom);
        nouvelAliment.setDescriptionAliment(description);

        // Liaison avec l'étape (Catégorie d'animal concerné)
        Etape etape = etapeRepository.findById(etapeId)
                .orElseThrow(() -> new RuntimeException("Étape/Catégorie introuvable avec l'ID: " + etapeId));
        nouvelAliment.setCategorieAnimalConcerne(etape);

        // 3. Traitement et ajout des lignes de composition
        for (LigneSaisieMatiereDTO saisie : ingredientsSaisis) {

            // 1. On charge la fiche technique de l'ingrédient depuis la base de données
            MatierePremiere mp = matierePremiereRepository.findById(saisie.matierePremiereId)
                    .orElseThrow(
                            () -> new RuntimeException("Matière première introuvable ID: " + saisie.matierePremiereId));

            // 2. VERIFICATION : On compare la proportion demandée (saisie) avec les limites
            // de la fiche technique (mp)
            if (saisie.proportion < mp.getProportionMin()) {
                throw new IllegalArgumentException("Erreur de formulation pour " + mp.getNom() +
                        " : La proportion demandée (" + saisie.proportion
                        + "%) est en dessous du seuil minimal de sécurité (" + mp.getProportionMin() + "%)");
            }

            if (saisie.proportion > mp.getProportionMax()) {
                throw new IllegalArgumentException("Erreur de formulation pour " + mp.getNom() +
                        " : La proportion demandée (" + saisie.proportion + "%) dépasse le seuil maximal autorisé ("
                        + mp.getProportionMax() + "%)");
            }

            // 3. Si les limites sont respectées, on enregistre la proportion finale dans la
            // recette
            LigneCompositionAliment ligne = new LigneCompositionAliment();
            ligne.setProportion(saisie.proportion); // Stocké uniquement dans la ligne de composition
            ligne.setIngredient(mp);

            // Ajout à l'Aliment
            nouvelAliment.ajouterLigne(ligne);
        }

        // 4. Sauvegarde unique (sauvegarde l'aliment ET toutes ses lignes de
        // composition)
        return alimentRepository.save(nouvelAliment);
    }

    @Transactional
    public Aliment corrigerCompositionDirecte(Long alimentId, List<LigneSaisieMatiereDTO> nouveauxIngredients) {
        Aliment aliment = alimentRepository.findById(alimentId)
                .orElseThrow(() -> new RuntimeException("Aliment introuvable avec l'ID : " + alimentId));

        // On vide proprement les anciennes lignes (orphanRemoval va les supprimer de la
        // base)
        aliment.getLignesComposition().clear();

        // On valide et on ajoute les nouvelles lignes
        validerEtConstruireLignes(aliment, nouveauxIngredients);

        return alimentRepository.save(aliment);
    }

    /**
     * STRATÉGIE 2 : Créer une nouvelle version de la formule (Évolution
     * nutritionnelle)
     */
    @Transactional
    public Aliment faireEvoluerFormuleVersion(Long alimentId, List<LigneSaisieMatiereDTO> nouveauxIngredients) {
        // 1. Récupérer l'ancienne version
        Aliment ancienneVersion = alimentRepository.findById(alimentId)
                .orElseThrow(() -> new RuntimeException("Aliment introuvable avec l'ID : " + alimentId));

        // 2. Désactiver l'ancienne version pour qu'elle ne soit plus utilisée à
        // l'avenir
        ancienneVersion.setActif(false);
        alimentRepository.save(ancienneVersion);

        // 3. Créer le nouvel aliment (Copie conforme mais avec une version supérieure)
        Aliment nouvelleVersion = new Aliment();
        nouvelleVersion.setNomAliment(ancienneVersion.getNomAliment());
        nouvelleVersion.setDescriptionAliment(ancienneVersion.getDescriptionAliment());
        nouvelleVersion.setCategorieAnimalConcerne(ancienneVersion.getCategorieAnimalConcerne());
        nouvelleVersion.setVersion(ancienneVersion.getVersion() + 1); // Version +1
        nouvelleVersion.setActif(true); // C'est la version officielle active désormais

        // 4. Valider et associer la nouvelle recette
        validerEtConstruireLignes(nouvelleVersion, nouveauxIngredients);

        return alimentRepository.save(nouvelleVersion);
    }

    /**
     * Méthode outil partagée pour valider les règles métier à 100% et les seuils
     * min/max
     */
    private void validerEtConstruireLignes(Aliment aliment, List<LigneSaisieMatiereDTO> saisies) {
        double totalProportion = saisies.stream().mapToDouble(LigneSaisieMatiereDTO::getProportion).sum();
        if (Math.abs(totalProportion - 100.0) > 0.01) {
            throw new IllegalArgumentException(
                    "La somme des proportions doit faire exactement 100%. Actuel : " + totalProportion + "%");
        }

        for (LigneSaisieMatiereDTO saisie : saisies) {
            MatierePremiere mp = matierePremiereRepository.findById(saisie.getMatierePremiereId())
                    .orElseThrow(() -> new RuntimeException(
                            "Matière première introuvable ID : " + saisie.getMatierePremiereId()));

            if (saisie.getProportion() < mp.getProportionMin()
                    || saisie.getProportion() > mp.getProportionMax()) {
                throw new IllegalArgumentException("La proportion pour " + mp.getNom() + " (" + saisie.getProportion()
                        + "%) enfreint les seuils autorisés (" + mp.getProportionMin() + "% - "
                        + mp.getProportionMax() + "%)");
            }

            LigneCompositionAliment ligne = new LigneCompositionAliment();
            ligne.setProportion(saisie.getProportion());
            ligne.setIngredient(mp);

            aliment.ajouterLigne(ligne);
        }
    }
}