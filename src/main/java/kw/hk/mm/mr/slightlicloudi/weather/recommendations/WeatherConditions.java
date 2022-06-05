package kw.hk.mm.mr.slightlicloudi.weather.recommendations;
import kw.hk.mm.mr.slightlicloudi.weather.mapping.Weather;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeatherConditions {
    Map<String, ClothingRecommendations> weatherConditionsMap = new HashMap<>();
    public WeatherConditions() {
        this.weatherConditionsMap.put("freezing,thunderstorm", new ClothingRecommendations(List.of(
                Clothes.THERMAL_CLOTHING,
                Clothes.WATERPROOF_CLOTHING,
                Clothes.LAYERED_CLOTHING,
                Clothes.LIGHTNING_PRECAUTIONS,
                Clothes.RAIN_BOOTS,
                Clothes.SPARE_CLOTHES,
                Clothes.NO_UMBRELLA
                ), null));
        this.weatherConditionsMap.put("freezing,drizzle", new ClothingRecommendations(List.of(
                Clothes.THERMAL_CLOTHING,
                Clothes.LAYERED_CLOTHING,
                Clothes.UMBRELLA,
                Clothes.GLOVES,
                Clothes.SCARF
                ), List.of(
                        Clothes.NO_UMBRELLA
        )));
        this.weatherConditionsMap.put("freezing,rain", new ClothingRecommendations(List.of(
                Clothes.THERMAL_CLOTHING,
                Clothes.RAINCOAT,
                Clothes.LAYERED_CLOTHING,
                Clothes.RAIN_BOOTS,
                Clothes.UMBRELLA,
                Clothes.GLOVES,
                Clothes.SCARF
        ), List.of(
                Clothes.NO_UMBRELLA
        )));
        this.weatherConditionsMap.put("freezing,snow", new ClothingRecommendations(List.of(
                Clothes.THERMAL_CLOTHING,
                Clothes.LAYERED_CLOTHING,
                Clothes.WINTER_JACKET,
                Clothes.WINTER_SHOES,
                Clothes.WINTER_HAT,
                Clothes.WINTER_GLOVES,
                Clothes.SCARF,
                Clothes.WOOL_SOCKS,
                Clothes.FACE_CREAM
        ), List.of(
                Clothes.WINTER_MASK
        )));
        this.weatherConditionsMap.put("freezing,clear", new ClothingRecommendations(List.of(
                Clothes.THERMAL_CLOTHING,
                Clothes.WOOL_SOCKS,
                Clothes.SCARF,
                Clothes.FACE_CREAM,
                Clothes.SUNGLASSES,
                Clothes.GLOVES,
                Clothes.HAT
        ), null));
        this.weatherConditionsMap.put("freezing,clouds", new ClothingRecommendations(List.of(
                Clothes.THERMAL_CLOTHING,
                Clothes.WOOL_SOCKS,
                Clothes.SCARF,
                Clothes.GLOVES,
                Clothes.HAT
        ), null));
        this.weatherConditionsMap.put("cold,thunderstorm", new ClothingRecommendations(List.of(
                Clothes.LAYERED_CLOTHING,
                Clothes.WATERPROOF_CLOTHING,
                Clothes.LIGHTNING_PRECAUTIONS,
                Clothes.RAIN_BOOTS,
                Clothes.SPARE_CLOTHES,
                Clothes.NO_UMBRELLA
        ), null));
        this.weatherConditionsMap.put("cold,drizzle", new ClothingRecommendations(List.of(
                Clothes.LAYERED_CLOTHING,
                Clothes.UMBRELLA
        ), List.of(
                Clothes.NO_UMBRELLA,
                Clothes.SCARF,
                Clothes.HAT
        )));
        this.weatherConditionsMap.put("cold,rain", new ClothingRecommendations(List.of(
                Clothes.LAYERED_CLOTHING,
                Clothes.RAINCOAT,
                Clothes.RAIN_BOOTS,
                Clothes.UMBRELLA
        ), List.of(
                Clothes.NO_UMBRELLA,
                Clothes.SCARF,
                Clothes.HAT
        )));
        this.weatherConditionsMap.put("cold,snow", new ClothingRecommendations(List.of(
                Clothes.THERMAL_CLOTHING,
                Clothes.LAYERED_CLOTHING,
                Clothes.WINTER_JACKET,
                Clothes.WINTER_SHOES,
                Clothes.WINTER_HAT,
                Clothes.WINTER_GLOVES,
                Clothes.SCARF,
                Clothes.WOOL_SOCKS,
                Clothes.FACE_CREAM
        ), List.of(
                Clothes.WINTER_MASK
        )));
        this.weatherConditionsMap.put("cold,clear", new ClothingRecommendations(List.of(
                Clothes.JACKET,
                Clothes.GLOVES,
                Clothes.HAT,
                Clothes.FACE_CREAM,
                Clothes.SUNGLASSES
        ), null));
        this.weatherConditionsMap.put("cold,clouds", new ClothingRecommendations(List.of(
                Clothes.JACKET,
                Clothes.GLOVES,
                Clothes.HAT
        ), List.of(
                Clothes.SCARF,
                Clothes.TURTLENECK
        )));
        this.weatherConditionsMap.put("warm,thunderstorm", new ClothingRecommendations(List.of(
                Clothes.WATERPROOF_CLOTHING,
                Clothes.LIGHTNING_PRECAUTIONS,
                Clothes.RAIN_BOOTS,
                Clothes.SPARE_CLOTHES,
                Clothes.NO_UMBRELLA
        ), null));
        this.weatherConditionsMap.put("warm,drizzle", new ClothingRecommendations(List.of(
                Clothes.UMBRELLA
        ), List.of(
                Clothes.NO_UMBRELLA,
                Clothes.LONG_SLEEVE
        )));
        this.weatherConditionsMap.put("warm,rain", new ClothingRecommendations(List.of(
                Clothes.RAIN_CAPE,
                Clothes.RAIN_BOOTS,
                Clothes.UMBRELLA
        ), List.of(
                Clothes.NO_UMBRELLA
        )));
        this.weatherConditionsMap.put("warm,snow", new ClothingRecommendations(List.of(
                Clothes.EASTER_EGG
        ), null));
        this.weatherConditionsMap.put("warm,clear", new ClothingRecommendations(List.of(
                Clothes.SHOES,
                Clothes.T_SHIRT,
                Clothes.SUNGLASSES,
                Clothes.SPF
        ), null));
        this.weatherConditionsMap.put("warm,clouds", new ClothingRecommendations(List.of(
                Clothes.SHOES,
                Clothes.T_SHIRT
        ), List.of(
                Clothes.LONG_SLEEVE
        )));
        this.weatherConditionsMap.put("hot,thunderstorm", new ClothingRecommendations(List.of(
                Clothes.WATERPROOF_CLOTHING,
                Clothes.LIGHTNING_PRECAUTIONS,
                Clothes.RAIN_CAPE,
                Clothes.SPARE_CLOTHES,
                Clothes.WATER,
                Clothes.NO_UMBRELLA
        ), null));
        this.weatherConditionsMap.put("hot,drizzle", new ClothingRecommendations(List.of(
                Clothes.SHOES, Clothes.UMBRELLA, Clothes.WATER
        ), List.of(
                Clothes.NO_UMBRELLA
        )));
        this.weatherConditionsMap.put("hot,rain", new ClothingRecommendations(List.of(
                Clothes.RAIN_CAPE,
                Clothes.RAIN_BOOTS,
                Clothes.UMBRELLA,
                Clothes.WATER
        ), List.of(
                Clothes.NO_UMBRELLA
        )));
        this.weatherConditionsMap.put("hot,snow", new ClothingRecommendations(List.of(
                Clothes.EASTER_EGG
        ), null));
        this.weatherConditionsMap.put("hot,clear", new ClothingRecommendations(List.of(
                Clothes.SHOES,
                Clothes.AIRY_CLOTHES,
                Clothes.CAP,
                Clothes.SPF,
                Clothes.WATER
        ), null));
        this.weatherConditionsMap.put("hot,clouds", new ClothingRecommendations(List.of(
                Clothes.SHOES,
                Clothes.AIRY_CLOTHES,
                Clothes.CAP,
                Clothes.SPF,
                Clothes.WATER
        ), List.of(
                Clothes.HAIRSPRAY
        )));

    }

    private String getTemperatureDescription(double temperature) {
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

    private String getWeatherDescription(Weather weather) {
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

    public ClothingRecommendations getClothingRecommendations(Weather weather, double temperature) {
        String weatherDescription = getWeatherDescription(weather);
        String temperatureDescription = getTemperatureDescription(temperature);

        return this.weatherConditionsMap.get(temperatureDescription + "," + weatherDescription);
    }


}
