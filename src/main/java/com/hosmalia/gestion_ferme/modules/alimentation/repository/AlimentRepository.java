package com.hosmalia.gestion_ferme.modules.alimentation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hosmalia.gestion_ferme.modules.alimentation.entity.Aliment;

public interface AlimentRepository extends JpaRepository<Aliment, Long> {

}
