package com.github.imgabreuw;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cities")
public class CityController {

    private final ChatClient client;

    private final WeatherService weatherService;

    public CityController(ChatClient.Builder client, WeatherService weatherService) {
        this.client = client
                .defaultSystem("You are a helpful AI assistante answering questions about cities around the world.")
                .defaultFunctions("currentWeatherFunction") // This "functions" can be used to provide to LLMs real time data, for example.
                .build();
        this.weatherService = weatherService;
    }

    @GetMapping
    public String cities(@RequestParam String message) {
        return client
                .prompt()
                .user(message)
                .call()
                .content();
    }

    @GetMapping("/weather")
    public ResponseEntity<WeatherService.WeatherResponse> weather(@RequestParam String city) {
        WeatherService.WeatherRequest request = new WeatherService.WeatherRequest(city);
        WeatherService.WeatherResponse weather = weatherService.findWeather(request);

        return ResponseEntity.ok(weather);
    }

}
