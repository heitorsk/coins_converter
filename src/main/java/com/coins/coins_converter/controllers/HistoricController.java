package com.coins.coins_converter.controllers;

import com.coins.coins_converter.entities.HistoricEntity;
import com.coins.coins_converter.services.HistoricCoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/historic")
public class HistoricController {

    @Autowired
    HistoricCoinService historicCoinService;

    @GetMapping
    public ResponseEntity<String> findAllHistoric(){
        List<HistoricEntity> result = historicCoinService.getAllHistoric();
        return ResponseEntity.ok(historicCoinService.getHistoricMessage(result));
    }

    @GetMapping("/{typeCoin}")
    public ResponseEntity<String> findHistoricByTypeCoin(@PathVariable String typeCoin){
        List<HistoricEntity> result = historicCoinService.getHistoricByTypeCoin(typeCoin);
        return ResponseEntity.ok(historicCoinService.getHistoricMessage(result));
    }


}
