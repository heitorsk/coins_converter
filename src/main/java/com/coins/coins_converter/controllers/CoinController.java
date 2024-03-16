package com.coins.coins_converter.controllers;

import com.coins.coins_converter.dto.CoinDto;
import com.coins.coins_converter.dto.HistoricDto;

import com.coins.coins_converter.services.CoinConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
public class CoinController {

    @Autowired
    private CoinConversionService coinConversionService;

    @GetMapping
    public ResponseEntity<String> infoApi(){
        return ResponseEntity.ok("""
                /coin
                 * Descrição: Este endpoint permite a conversão entre duas moedas.
                 * Método: GET
                 * Corpo da Requisição (JSON): { "typeCoin": "USD-BRL", "valueCoin": "10" }
                    - Onde "USD" é a moeda a ser convertida e "BRL" é a moeda de destino. "valueCoin" especifica a quantidade da moeda a ser convertida.
                    
                /historic
                 * Descrição: Este endpoint fornece acesso ao histórico completo de todas as transações de conversão de moeda.
                 * Método: GET
                 
                /historic/{moeda}
                 * Descrição: Este endpoint permite visualizar todas as transações envolvendo uma conversão específica.
                 * Método: GET
                 * Parâmetro de Caminho: {moeda} (por exemplo, "USD-BRL")
                """);
    }

    @GetMapping("/coin")
    public ResponseEntity<String> checkCoin(@RequestBody CoinDto coinDto) {
        String result = coinConversionService.convertCoin(coinDto);
        return ResponseEntity.ok(result);
    }
}
