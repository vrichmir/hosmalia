package com.hosmalia.gestion_ferme.modules.alimentation.service;

import com.hosmalia.gestion_ferme.modules.alimentation.dto.MatierePremiereCreationDTO;
import com.hosmalia.gestion_ferme.modules.alimentation.entity.HistoriquePrix;
import com.hosmalia.gestion_ferme.modules.alimentation.entity.MatierePremiere;
import com.hosmalia.gestion_ferme.modules.alimentation.repository.HistoriquePrixRepository;
import com.hosmalia.gestion_ferme.modules.alimentation.repository.MatierePremiereRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class MatierePremiereService {

    private final MatierePremiereRepository matierePremiereRepository;
    private final HistoriquePrixRepository historiquePrixRepository; // AJOUTÉ

    // Injection par constructeur des deux dépôts
    public MatierePremiereService(MatierePremiereRepository matierePremiereRepository,
            HistoriquePrixRepository historiquePrixRepository) {
        this.matierePremiereRepository = matierePremiereRepository;
        this.historiquePrixRepository = historiquePrixRepository;
    }

    /**
     * Crée une nouvelle matière première dans le catalogue de la ferme avec ses
     * contraintes de dosage.
     */
    @Transactional
    public MatierePremiere enregistrerMatierePremiere(MatierePremiereCreationDTO dto) {

        // 1. Validation de cohérence des contraintes de formulation
        if (dto.getProportionMinAutorisee() < 0 || dto.getProportionMaxAutorisee() > 100) {
            throw new IllegalArgumentException("Les proportions autorisées doivent être comprises entre 0% et 100%.");
        }

        if (dto.getProportionMinAutorisee() > dto.getProportionMaxAutorisee()) {
            throw new IllegalArgumentException("La proportion minimale (" + dto.getProportionMinAutorisee()
                    + "%) ne peut pas être supérieure à la proportion maximale (" + dto.getProportionMaxAutorisee()
                    + "%).");
        }

        // 2. Vérification d'unicité pour éviter les doublons (ex: deux fois le "Maïs")
        if (matierePremiereRepository.findByNomIgnoreCase(dto.getNom()).isPresent()) {
            throw new IllegalArgumentException(
                    "La matière première '" + dto.getNom() + "' existe déjà dans le catalogue.");
        }

        // 3. Cartographie du DTO vers l'Entité
        MatierePremiere mp = new MatierePremiere();
        mp.setNom(dto.getNom());
        mp.setPrixActuelParKg(dto.getPrixInitialParKg());
        mp.setProportionMin(dto.getProportionMinAutorisee());
        mp.setProportionMax(dto.getProportionMaxAutorisee());

        // Sauvegarde d'abord pour générer l'ID de la matière première
        MatierePremiere mpSauvee = matierePremiereRepository.save(mp);

        // 3. AJOUTÉ : Création instantanée de la première ligne d'historique de prix
        HistoriquePrix premierPrix = new HistoriquePrix();
        premierPrix.setPrixParKg(dto.getPrixInitialParKg());
        premierPrix.setDateEffet(LocalDate.now()); // Période qui démarre aujourd'hui
        premierPrix.setDateFin(null); // Reste ouverte (active)
        premierPrix.setMatierePremiere(mpSauvee); // Associe l'ID fraîchement généré

        // Sauvegarde de la ligne d'historique
        historiquePrixRepository.save(premierPrix);

        return mpSauvee;
    }

    /**
     * Récupère la liste de toutes les matières premières disponibles.
     */
    public List<MatierePremiere> listerToutes() {
        return matierePremiereRepository.findAll();
    }
}