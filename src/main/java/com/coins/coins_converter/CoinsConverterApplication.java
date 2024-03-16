package com.coins.coins_converter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CoinsConverterApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoinsConverterApplication.class, args);
	}


}
