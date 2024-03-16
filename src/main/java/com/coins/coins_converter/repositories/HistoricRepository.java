package com.coins.coins_converter.repositories;

import com.coins.coins_converter.entities.HistoricEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoricRepository extends JpaRepository<HistoricEntity, String> {

    List<HistoricEntity> findIdByCoinTypeCoin(String typeCoin);
}
