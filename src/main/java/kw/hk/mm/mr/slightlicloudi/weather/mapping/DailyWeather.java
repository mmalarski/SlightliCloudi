package kw.hk.mm.mr.slightlicloudi.weather.mapping;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DailyWeather {
    private long dt;
    private long sunrise;
    private long sunset;
    private long moonrise;
    private long moonset;
    private double moonPhase;
    private Temperature temp;
    private FeelsLike feelsLike;
    private long pressure;
    private long humidity;
    private double dewPoint;
    private double windSpeed;
    private double windGust;
    private long windDeg;
    private long clouds;
    private double uvi;
    private double pop;
    private Double rain;
    private Double snow;
    private Weather[] weather;
}
