package com.hosmalia.gestion_ferme.modules.alimentation.controller;

import com.hosmalia.gestion_ferme.modules.alimentation.dto.ChangementPrixDTO;
import com.hosmalia.gestion_ferme.modules.alimentation.dto.MatierePremiereCreationDTO;
import com.hosmalia.gestion_ferme.modules.alimentation.entity.MatierePremiere;
import com.hosmalia.gestion_ferme.modules.alimentation.service.HistoriquePrixMatiereService;
import com.hosmalia.gestion_ferme.modules.alimentation.service.MatierePremiereService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matieres-premieres")
public class MatierePremiereController {

    private final MatierePremiereService matierePremiereService;
    private final HistoriquePrixMatiereService historiquePrixMatiereService;

    // Injection par constructeur
    public MatierePremiereController(MatierePremiereService matierePremiereService,
            HistoriquePrixMatiereService historiquePrixMatiereService) {
        this.matierePremiereService = matierePremiereService;
        this.historiquePrixMatiereService = historiquePrixMatiereService;
    }

    /**
     * Endpoint pour créer une nouvelle matière première
     * URL : POST http://localhost:8080/api/matieres-premieres
     */
    @PostMapping
    public ResponseEntity<MatierePremiere> creerMatierePremiere(@RequestBody MatierePremiereCreationDTO dto) {
        MatierePremiere nouvelleMatiere = matierePremiereService.enregistrerMatierePremiere(dto);
        return new ResponseEntity<>(nouvelleMatiere, HttpStatus.CREATED);

    }

    /**
     * Endpoint pour lister toutes les matières premières
     * URL : GET http://localhost:8080/api/matieres-premieres
     */
    @GetMapping
    public ResponseEntity<List<MatierePremiere>> listerToutes() {
        List<MatierePremiere> matieres = matierePremiereService.listerToutes();
        return ResponseEntity.ok(matieres);
    }

    /**
     * Endpoint pour modifier le prix d'un ingrédient (clôture l'ancien et crée le
     * nouveau)
     * URL : POST http://localhost:8080/api/matieres-premieres/changer-prix
     */
    @PostMapping("/changer-prix")
    public ResponseEntity<String> changerPrixMatiere(@RequestBody ChangementPrixDTO dto) {
        try {
            historiquePrixMatiereService.modifierPrixMatiere(dto);
            return ResponseEntity.ok("Le prix de la matière première a été mis à jour avec succès.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}