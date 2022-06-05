package kw.hk.mm.mr.slightlicloudi.weather.mapping;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Temperature {
    private double morn;
    private double day;
    private double eve;
    private double night;
    private double min;
    private double max;
}
