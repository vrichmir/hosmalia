package com.hosmalia.gestion_ferme.modules.alimentation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hosmalia.gestion_ferme.modules.alimentation.entity.MouvementStock;

public interface MouvementStockRepository extends JpaRepository<MouvementStock, Long> {
}
