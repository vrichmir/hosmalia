package com.hosmalia.gestion_ferme.modules.alimentation.dto;

import lombok.Data;

@Data
public class MatierePremiereCreationDTO {
    private String nom;
    private Double prixInitialParKg;
    private double proportionMinAutorisee;
    private double proportionMaxAutorisee;
}
