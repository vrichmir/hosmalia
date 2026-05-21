package com.hosmalia.gestion_ferme.modules.alimentation.controller;

import com.hosmalia.gestion_ferme.modules.alimentation.dto.LigneSaisieMatiereDTO;
import com.hosmalia.gestion_ferme.modules.alimentation.dto.AlimentDTO;
import com.hosmalia.gestion_ferme.modules.alimentation.entity.Aliment;
import com.hosmalia.gestion_ferme.modules.alimentation.service.AlimentService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/aliments")
public class AlimentController {

    private final AlimentService alimentService;

    public AlimentController(AlimentService alimentService) {
        this.alimentService = alimentService;
    }

    /**
     * Endpoint pour formuler et enregistrer un nouvel aliment
     * URL : POST http://localhost:8080/api/aliments
     */
    @PostMapping
    public ResponseEntity<?> concevoirAliment(@RequestBody AlimentDTO dto) {
        try {
            Aliment nouvelAliment = alimentService.creerAliment(
                    dto.getNomAliment(),
                    dto.getDescriptionAliment(),
                    dto.getCategorieAnimalId(),
                    dto.getIngredients());
            return new ResponseEntity<>(nouvelAliment, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            // Renvoie une erreur 400 claire si le total ne fait pas 100% ou si un
            // ingrédient dépasse ses bornes
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            // Renvoie une erreur 404 si la catégorie d'animal ou un ingrédient n'existe pas
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * URL pour la Stratégie 1 : Correction directe (Écrase la version actuelle)
     * PUT http://localhost:8080/api/aliments/{id}/corriger
     */
    @PutMapping("/{id}/corriger")
    public ResponseEntity<?> corrigerAliment(@PathVariable Long id,
            @RequestBody List<LigneSaisieMatiereDTO> ingredients) {
        try {
            Aliment aliment = alimentService.corrigerCompositionDirecte(id, ingredients);
            return ResponseEntity.ok(aliment);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * URL pour la Stratégie 2 : Évolution (Désactive l'ancien, crée un V+1)
     * PUT http://localhost:8080/api/aliments/{id}/evoluer
     */
    @PutMapping("/{id}/evoluer")
    public ResponseEntity<?> evoluerAliment(@PathVariable Long id,
            @RequestBody List<LigneSaisieMatiereDTO> ingredients) {
        try {
            Aliment nouvelleVersion = alimentService.faireEvoluerFormuleVersion(id, ingredients);
            return ResponseEntity.status(HttpStatus.CREATED).body(nouvelleVersion);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
