package com.github.imgabreuw;

import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.function.Function;

@Service
public class WeatherFunction implements Function<WeatherService.WeatherRequest, WeatherService.WeatherResponse> {

    private static final Logger log = LoggerFactory.getLogger(WeatherService.class);

    private final RestClient restClient;

    public WeatherFunction() {
        this.restClient = RestClient.create("http://localhost:8080");
    }

    @Override
    public WeatherService.WeatherResponse apply(WeatherService.WeatherRequest request) {
        log.info("Requesting weather for {}", request);

        WeatherService.WeatherResponse response = restClient.get()
                .uri("/cities/weather?city={city}", request.city())
                .header("Accept", "application/json")
                .retrieve()
                .body(WeatherService.WeatherResponse.class);

        log.info("Returning weather for {}", response);

        return null;
    }

}
