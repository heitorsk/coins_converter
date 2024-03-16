package com.coins.coins_converter.services;

import com.coins.coins_converter.dto.CoinDto;
import com.coins.coins_converter.dto.HistoricDto;
import com.coins.coins_converter.entities.CoinEntity;
import com.coins.coins_converter.repositories.CoinRepository;
import com.coins.coins_converter.repositories.HistoricRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class CoinConversionService {

    @Autowired
    private CoinRepository coinRepository;

    @Autowired
    private HistoricRepository historicRepository;

    public String convertCoin(CoinDto coinDTO) {
        try {
            CoinEntity coin = getCoin(coinDTO);

            if (coin == null) {
                return "Ocorreu um erro. Por favor, verifique o tipo de moeda, pois pode ser inválido, ou os parâmetros podem estar incorretos. Por favor, tente novamente!";
            }

            JSONObject dataJson = fetchDataFromApi(coinDTO.getTypeCoin());
            Double valueCoin = coinDTO.getValueCoin();
            Double valueConverted = calculateValueConverted(dataJson, valueCoin);

            HistoricDto historic = createHistoricObject(dataJson, coin, valueConverted);
            historicRepository.save(historic.transformToObject());

            return generateMessage(dataJson, coinDTO.getTypeCoin(), valueCoin, valueConverted);
        } catch (Exception e) {
            return "Ocorreu um erro ao realizar a consulta: " + e.getCause().getMessage();
        }

    }

    private CoinEntity getCoin(CoinDto coinDTO) {
        String typeCoin = coinDTO.getTypeCoin();
        return coinRepository.findIdByTypeCoin(typeCoin).orElse(null);
    }

    private JSONObject fetchDataFromApi(String typeCoin) {
        String apiUrl = "https://economia.awesomeapi.com.br/last/" + typeCoin;
        RestTemplate restTemplate = new RestTemplate();
        String jsonResponse = restTemplate.getForObject(apiUrl, String.class);
        return new JSONObject(jsonResponse).getJSONObject(typeCoin.replace("-", ""));
    }

    private Double calculateValueConverted(JSONObject dataJson, Double valueCoin) {
        Double ask = dataJson.getDouble("ask");
        return ask * valueCoin;
    }

    private HistoricDto createHistoricObject(JSONObject dataJson, CoinEntity coin, Double valueConverted) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String createDate = dataJson.getString("create_date");

        HistoricDto historic = new HistoricDto();
        historic.setDateSearch(LocalDateTime.now());
        historic.setCreateDate(LocalDateTime.parse(createDate, formatter));
        historic.setAsk(dataJson.getDouble("ask"));
        historic.setValueConverted(valueConverted);
        historic.setCoin(coin);

        return historic;
    }

    private String generateMessage(JSONObject dataJson, String typeCoin, Double valueCoin, Double valueConverted) {
        String name = dataJson.getString("name");
        String code = dataJson.getString("code");
        String codein = dataJson.getString("codein");
//      String high = dataJson.getString("high");
//      String low = dataJson.getString("low");
//      String pctChange = dataJson.getString("pctChange");
//      String bid = dataJson.getString("bid");
//      Double varBid = dataJson.getDouble("varBid");
//      String timestamp = dataJson.getString("timestamp");
//      String createDate = dataJson.getString("create_date");
//      Double ask = dataJson.getDouble("ask");

        return "Conversão: " + name + "\nMoeda: " + typeCoin + "\nValor informado pelo usuário (" + code + "): " + valueCoin + "\nValor convertido (" + codein + "): " + valueConverted.floatValue();
    }

}
