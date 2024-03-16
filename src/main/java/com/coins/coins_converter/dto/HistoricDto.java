package com.coins.coins_converter.dto;


import com.coins.coins_converter.entities.CoinEntity;
import com.coins.coins_converter.entities.HistoricEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoricDto {

    private String id;

    private CoinEntity coin;

    private Double ask;

    private Double valueConverted;

    private LocalDateTime dateSearch;

    private LocalDateTime createDate;


    public HistoricEntity transformToObject(){
        return new HistoricEntity(id, coin, ask, valueConverted, dateSearch, createDate);
    }
}
