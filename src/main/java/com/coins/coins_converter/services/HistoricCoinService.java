package com.coins.coins_converter.services;

import com.coins.coins_converter.entities.HistoricEntity;
import com.coins.coins_converter.repositories.HistoricRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class HistoricCoinService {

    @Autowired
    private HistoricRepository historicRepository;

    public List<HistoricEntity> getAllHistoric(){
        return historicRepository.findAll();
    }

    public List<HistoricEntity> getHistoricByTypeCoin(String typeCoin){
        return historicRepository.findIdByCoinTypeCoin(typeCoin);
    }

    public String getHistoricMessage(List<HistoricEntity> historicList) {
        StringBuilder result = new StringBuilder();
        DecimalFormat df = new DecimalFormat("#.##");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        int i = 1;
        for (HistoricEntity historic : historicList) {
            result.append(i++).append(": ")
                  .append("Conversão: ").append(historic.getCoin().getConverterCoin().replace("/", " para "))
                  .append("\n ").append("Valor informado: ").append(df.format(calculateInformedValue(historic.getValueConverted(), historic.getAsk())).replace(",", "."))
                  .append("\n ").append("Valor convertido: ").append(df.format(historic.getValueConverted()).replace(",", "."))
                  .append("\n ").append("Data da pesquisa: ").append(historic.getDateSearch().format(formatter))
                  .append("\n\n");
        }

        return result.toString();
    }


    public Double calculateInformedValue(Double valueConverted, Double valueAsk){
        double result = valueConverted / valueAsk;
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.valueOf(df.format(result));
    }
}
