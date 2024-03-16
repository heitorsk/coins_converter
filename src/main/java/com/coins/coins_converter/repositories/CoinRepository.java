package com.coins.coins_converter.repositories;

import com.coins.coins_converter.entities.CoinEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CoinRepository extends JpaRepository<CoinEntity, String> {
     Optional<CoinEntity> findIdByTypeCoin(String coin);
}
