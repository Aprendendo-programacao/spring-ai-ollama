package com.github.imgabreuw;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import java.util.function.Function;

@Configuration
public class FunctionConfiguration {

    @Bean
    @Description("Get the current weather conditions for the given city.")
    public Function<WeatherService.WeatherRequest,WeatherService.WeatherResponse> currentWeatherFunction() {
        return new WeatherFunction();
    }

}
