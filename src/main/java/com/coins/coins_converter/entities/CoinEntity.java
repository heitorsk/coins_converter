package com.coins.coins_converter.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "coin")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoinEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "converter_coin")
    private String converterCoin;

    @Column(name = "type_coin")
    private String typeCoin;

}
