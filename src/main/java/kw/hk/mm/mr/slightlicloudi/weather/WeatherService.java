package kw.hk.mm.mr.slightlicloudi.weather;

import kw.hk.mm.mr.slightlicloudi.weather.mapping.WeatherResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
@Slf4j
public class WeatherService {

    @Value("${weather_api_key}")
    private String API_KEY;
    WebClient client = WebClient.create();

public WeatherResponse getWeather(double latitude, double longitude){
    return client.get()
            .uri("https://api.openweathermap.org/data/2.5/onecall?lat=" + latitude + "&lon=" + longitude + "&appid=" + API_KEY)
            .retrieve()
            .bodyToMono(WeatherResponse.class)
            .block();
}

}
