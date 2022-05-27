package kw.hk.mm.mr.slightlicloudi.weather.mapping;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CurrentWeather  {
    private long dt;
    private long sunrise;
    private long sunset;
    private double temp;
    private double feelsLike;
    private long pressure;
    private long humidity;
    private double dewPoint;
    private long clouds;
    private double uvi;
    private long visibility;
    private long windSpeed;
    private long windGust;
    private long windDeg;
    private Weather[] weather;
    private long pop;
}
