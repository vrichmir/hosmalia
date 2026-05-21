package com.hosmalia.gestion_ferme.modules.alimentation.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ChangementPrixDTO {
    private Long matierePremiereId;
    private Double nouveauPrixParKg;
    private LocalDate dateEffet; // Date à laquelle le nouveau prix commence à s'appliquer
}