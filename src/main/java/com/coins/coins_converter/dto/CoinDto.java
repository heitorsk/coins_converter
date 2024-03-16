package com.coins.coins_converter.dto;


import com.coins.coins_converter.entities.CoinEntity;
import com.coins.coins_converter.entities.HistoricEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoinDto {

    private String id;
    private String converterCoin;
    private String typeCoin;
    private Double valueCoin;

    public CoinEntity transformToObject() {
        return new CoinEntity(id, converterCoin, typeCoin);
    }
}
