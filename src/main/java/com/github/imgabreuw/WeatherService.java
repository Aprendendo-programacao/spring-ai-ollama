package com.github.imgabreuw;

import org.springframework.stereotype.Service;

@Service
public class WeatherService {

    public WeatherResponse findWeather(WeatherRequest request) {
        return mockData();
    }

    private WeatherResponse mockData() {
        return new WeatherResponse("32.0", new Condition("Sunny"), "10.5", "50");
    }


    public record WeatherRequest(String city) {
    }

    public record WeatherResponse(String temp_f, Condition condition, String wind_mph, String humidity) {
    }

    public record Condition(String text) {
    }

}
