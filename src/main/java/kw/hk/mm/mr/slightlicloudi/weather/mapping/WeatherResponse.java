package kw.hk.mm.mr.slightlicloudi.weather.mapping;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class WeatherResponse {
    private double lat;
    private double lon;
    private String timezone;
    private long timezoneOffset;
    private CurrentWeather current;
    private MinutelyWeather[] minutely;
    private CurrentWeather[] hourly;
    private DailyWeather[] daily;
}