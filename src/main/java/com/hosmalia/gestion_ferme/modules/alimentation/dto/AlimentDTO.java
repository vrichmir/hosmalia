package com.hosmalia.gestion_ferme.modules.alimentation.dto;

import lombok.Data;
import java.util.List;

@Data
public class AlimentDTO {
    private String nomAliment;
    private String descriptionAliment;
    private Long categorieAnimalId; // L'ID de l'Etape/Catégorie concernée
    private List<MatierePremiereDto> ingredients;
}