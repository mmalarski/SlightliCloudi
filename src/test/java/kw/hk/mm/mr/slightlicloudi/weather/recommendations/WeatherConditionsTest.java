package kw.hk.mm.mr.slightlicloudi.weather.recommendations;

import kw.hk.mm.mr.slightlicloudi.configuration.BeanRegistrationContextInitializer;
import kw.hk.mm.mr.slightlicloudi.weather.mapping.DailyWeather;
import kw.hk.mm.mr.slightlicloudi.weather.mapping.Temperature;
import kw.hk.mm.mr.slightlicloudi.weather.mapping.Weather;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestConstructor;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Profile("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ContextConfiguration(initializers = BeanRegistrationContextInitializer.class)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class WeatherConditionsTest {
    DailyWeather testWeather = new DailyWeather();
    WeatherConditions weatherConditions = new WeatherConditions();

    public WeatherConditionsTest() {
        testWeather.setTemp(new Temperature());
        testWeather.setWeather(new Weather[]{ new Weather() });
    }
    @ParameterizedTest
    @CsvSource(value = {
            "-20|200|freezing,thunderstorm",
            "-20|300|freezing,drizzle",
            "-20|500|freezing,rain",
            "-20|601|freezing,snow",
            "-20|701|freezing,atmosphere",
            "-20|800|freezing,clear",
            "-20|805|freezing,clouds",
            "-20|999|freezing,unknown",
            "-2|200|cold,thunderstorm",
            "-2|300|cold,drizzle",
            "-2|500|cold,rain",
            "-2|601|cold,snow",
            "-2|701|cold,atmosphere",
            "-2|800|cold,clear",
            "-2|805|cold,clouds",
            "-2|999|cold,unknown",
            "10|200|warm,thunderstorm",
            "10|300|warm,drizzle",
            "10|500|warm,rain",
            "10|601|warm,snow",
            "10|701|warm,atmosphere",
            "10|800|warm,clear",
            "10|805|warm,clouds",
            "10|999|warm,unknown",
            "30|200|hot,thunderstorm",
            "30|300|hot,drizzle",
            "30|500|hot,rain",
            "30|601|hot,snow",
            "30|701|hot,atmosphere",
            "30|800|hot,clear",
            "30|805|hot,clouds",
            "30|999|hot,unknown",

    }, delimiter = '|')
    void testWeathers(int temperature, int atmosphereId, String expectedWeather) {
        testWeather.getTemp().setDay(temperature);
        testWeather.getWeather()[0].setId(atmosphereId);
        assertEquals(expectedWeather, weatherConditions.getWeatherDescriptionFromWeather(testWeather));
    }
}