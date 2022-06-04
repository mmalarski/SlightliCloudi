package kw.hk.mm.mr.slightlicloudi.weather.recommendations;
import kw.hk.mm.mr.slightlicloudi.weather.mapping.Weather;
import kw.hk.mm.mr.slightlicloudi.weather.mapping.WeatherResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeatherConditions {
    Map<String, List<Clothes>> weatherConditionsMap = new HashMap<>();
    // constructor
    public WeatherConditions() {
        this.weatherConditionsMap.put("freezing,thunderstorm", List.of(
                Clothes.THERMAL_CLOTHING,
                Clothes.WATERPROOF_CLOTHING,
                Clothes.LAYERED_CLOTHING,
                Clothes.LIGHTNING_PRECAUTIONS,
                Clothes.RAIN_BOOTS,
                Clothes.SPARE_CLOTHES,
                Clothes.NO_UMBRELLA
                ));
        this.weatherConditionsMap.put("freezing,drizzle", List.of(
                Clothes.THERMAL_CLOTHING,
                Clothes.LAYERED_CLOTHING,
                Clothes.UMBRELLA,
                Clothes.GLOVES,
                Clothes.SCARF
                ));
        this.weatherConditionsMap.put("freezing,rain", List.of(
                Clothes.THERMAL_CLOTHING,
                Clothes.RAINCOAT,
                Clothes.LAYERED_CLOTHING,
                Clothes.RAIN_BOOTS,
                Clothes.UMBRELLA,
                Clothes.GLOVES,
                Clothes.SCARF
        ));
        this.weatherConditionsMap.put("freezing,snow", List.of(
                Clothes.THERMAL_CLOTHING,
                Clothes.LAYERED_CLOTHING,
                Clothes.WINTER_JACKET,
                Clothes.WINTER_SHOES,
                Clothes.WINTER_HAT,
                Clothes.WINTER_GLOVES,
                Clothes.SCARF,
                Clothes.WOOL_SOCKS,
                Clothes.FACE_CREAM
        ));
        this.weatherConditionsMap.put("freezing,clear", List.of(
                Clothes.THERMAL_CLOTHING,
                Clothes.WOOL_SOCKS,
                Clothes.SCARF,
                Clothes.FACE_CREAM,
                Clothes.SUNGLASSES,
                Clothes.GLOVES,
                Clothes.HAT
        ));
        this.weatherConditionsMap.put("freezing,clouds", List.of(
                Clothes.THERMAL_CLOTHING,
                Clothes.WOOL_SOCKS,
                Clothes.SCARF,
                Clothes.GLOVES,
                Clothes.HAT
        ));
        this.weatherConditionsMap.put("cold,thunderstorm", List.of(
                Clothes.LAYERED_CLOTHING,
                Clothes.WATERPROOF_CLOTHING,
                Clothes.LIGHTNING_PRECAUTIONS,
                Clothes.RAIN_BOOTS,
                Clothes.SPARE_CLOTHES,
                Clothes.NO_UMBRELLA
        ));
        this.weatherConditionsMap.put("cold,drizzle", List.of(
                Clothes.LAYERED_CLOTHING,
                Clothes.UMBRELLA
        ));
        this.weatherConditionsMap.put("cold,rain", List.of(
                Clothes.LAYERED_CLOTHING,
                Clothes.RAINCOAT,
                Clothes.RAIN_BOOTS,
                Clothes.UMBRELLA
        ));
        this.weatherConditionsMap.put("cold,snow", List.of(
                Clothes.THERMAL_CLOTHING,
                Clothes.LAYERED_CLOTHING,
                Clothes.WINTER_JACKET,
                Clothes.WINTER_SHOES,
                Clothes.WINTER_HAT,
                Clothes.WINTER_GLOVES,
                Clothes.SCARF,
                Clothes.WOOL_SOCKS,
                Clothes.FACE_CREAM
        ));
        this.weatherConditionsMap.put("cold,clear", List.of(
                Clothes.JACKET,
                Clothes.GLOVES,
                Clothes.HAT,
                Clothes.FACE_CREAM,
                Clothes.SUNGLASSES
        ));
        this.weatherConditionsMap.put("cold,clouds", List.of(
                Clothes.JACKET,
                Clothes.GLOVES,
                Clothes.HAT
        ));
        this.weatherConditionsMap.put("warm,thunderstorm", List.of(
                Clothes.WATERPROOF_CLOTHING,
                Clothes.LIGHTNING_PRECAUTIONS,
                Clothes.RAIN_BOOTS,
                Clothes.SPARE_CLOTHES,
                Clothes.NO_UMBRELLA
        ));
        this.weatherConditionsMap.put("warm,drizzle", List.of(
                Clothes.UMBRELLA
        ));
        this.weatherConditionsMap.put("warm,rain", List.of(
                Clothes.RAIN_CAPE,
                Clothes.RAIN_BOOTS,
                Clothes.UMBRELLA
        ));
        this.weatherConditionsMap.put("warm,snow", List.of(
                Clothes.EASTER_EGG
        ));
        this.weatherConditionsMap.put("warm,clear", List.of(
                Clothes.SHOES,
                Clothes.T_SHIRT,
                Clothes.SUNGLASSES,
                Clothes.SPF
        ));
        this.weatherConditionsMap.put("warm,clouds", List.of(
                Clothes.SHOES,
                Clothes.T_SHIRT
        ));
        this.weatherConditionsMap.put("hot,thunderstorm", List.of(
                Clothes.WATERPROOF_CLOTHING,
                Clothes.LIGHTNING_PRECAUTIONS,
                Clothes.RAIN_CAPE,
                Clothes.SPARE_CLOTHES,
                Clothes.WATER,
                Clothes.NO_UMBRELLA
        ));
        this.weatherConditionsMap.put("hot,drizzle", List.of(
                Clothes.SHOES, Clothes.UMBRELLA, Clothes.WATER
        ));
        this.weatherConditionsMap.put("hot,rain", List.of(
                Clothes.RAIN_CAPE,
                Clothes.RAIN_BOOTS,
                Clothes.UMBRELLA,
                Clothes.WATER
        ));
        this.weatherConditionsMap.put("hot,snow", List.of(
                Clothes.EASTER_EGG
        ));
        this.weatherConditionsMap.put("hot,clear", List.of(
                Clothes.SHOES,
                Clothes.AIRY_CLOTHES,
                Clothes.CAP,
                Clothes.SPF,
                Clothes.WATER
        ));
        this.weatherConditionsMap.put("hot,clouds", List.of(
                Clothes.SHOES,
                Clothes.AIRY_CLOTHES,
                Clothes.CAP,
                Clothes.SPF,
                Clothes.WATER
        ));

    }

    public String getTemperatureDescription(double temperature) {
        if (temperature < -15) {
            return "freezing";
        } else if (temperature < 0) {
            return "cold";
        } else if (temperature < 15) {
            return "warm";
        } else {
            return "hot";
        }
    }

    public String getWeatherDescription(Weather weather) {
        if (weather.getId() < 300) {
            return "thunderstorm";
        } else if (weather.getId() < 400) {
            return "drizzle";
        } else if (weather.getId() < 600) {
            return "rain";
        } else if (weather.getId() < 700) {
            return "snow";
        } else if (weather.getId() < 800){
            return "atmosphere";
        } else if (weather.getId() == 800) {
            return "clear";
        } else if (weather.getId() < 900) {
            return "clouds";
        } else {
            return "unknown";
        }
    }





}
