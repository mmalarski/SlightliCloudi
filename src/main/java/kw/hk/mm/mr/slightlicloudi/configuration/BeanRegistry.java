package kw.hk.mm.mr.slightlicloudi.configuration;

import kw.hk.mm.mr.slightlicloudi.configuration.JWT.JWTFilter;
import kw.hk.mm.mr.slightlicloudi.configuration.JWT.JWTHandler;
import kw.hk.mm.mr.slightlicloudi.mailing.MailService;
import kw.hk.mm.mr.slightlicloudi.mailing.scheduling.MailScheduler;
import kw.hk.mm.mr.slightlicloudi.mailing.scheduling.WeatherMailSender;
import kw.hk.mm.mr.slightlicloudi.weather.WeatherService;
import kw.hk.mm.mr.slightlicloudi.weather.recommendations.WeatherConditions;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class BeanRegistry {
    static List<Class<?>> commonBeans = List.of(
            JWTHandler.class,
            JWTFilter.class,
            WeatherService.class,
            MailService.class,
            WeatherMailSender.class,
            WeatherConditions.class,
            MailScheduler.class
    );
}
