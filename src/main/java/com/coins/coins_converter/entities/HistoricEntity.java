package com.coins.coins_converter.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "historic")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoricEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "id_coin", referencedColumnName = "id")
    private CoinEntity coin;

    @Column(name = "ask")
    private Double ask;

    @Column(name = "valueConverted")
    private Double valueConverted;

    @Column(name = "date_search")
    private LocalDateTime dateSearch;

    @Column(name = "create_date")
    private LocalDateTime createDate;

}
