package com.hosmalia.gestion_ferme.modules.alimentation.dto;

import lombok.Data;

@Data
public class LigneSaisieMatiereDTO {
    public Long matierePremiereId;
    public double proportion;
    public double proportionMin;
    public double proportionMax;
}
