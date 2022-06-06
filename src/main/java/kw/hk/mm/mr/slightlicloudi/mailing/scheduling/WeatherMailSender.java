package kw.hk.mm.mr.slightlicloudi.mailing.scheduling;

import kw.hk.mm.mr.slightlicloudi.mailing.MailService;
import kw.hk.mm.mr.slightlicloudi.mailing.MailType;
import kw.hk.mm.mr.slightlicloudi.user.UserPreferences;
import kw.hk.mm.mr.slightlicloudi.weather.WeatherService;
import kw.hk.mm.mr.slightlicloudi.weather.mapping.DailyWeather;
import kw.hk.mm.mr.slightlicloudi.weather.recommendations.WeatherConditions;
import lombok.AllArgsConstructor;

import javax.mail.MessagingException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@AllArgsConstructor
public class WeatherMailSender {

    private static final int TEN_METERS_PER_SECOND = 10;
    MailService mailService;
    WeatherConditions weatherConditions;
    WeatherService weatherService;


    public void sendWeatherMail(UserPreferences userPreferences, MailType mailType) throws MessagingException {
        var templatePath = getTemplateName(mailType);
        Map<String, Object> templateParams = new HashMap<>();
        var weatherResponse = weatherService.getWeather(userPreferences.getLatitude(), userPreferences.getLongitude());
        var weathers = getWeatherSublist(weatherResponse.getDaily(), mailType);
        var windyDays = getWindyDays(weathers);
        var daysOfWeek = getDatesFromWeatherList(weathers);
        var weatherDescriptions = weathers.stream().map(weather -> weatherConditions.getWeatherDescriptionFromWeather(weather)).toList();
        var clothingRecommendations = weatherDescriptions.stream()
                .map(weatherDescription -> weatherConditions.getClothingRecommendationsFromWeatherDescription(weatherDescription)).toList();
        if (userPreferences.isClothingRecommendation()) {
            templateParams.put("weatherRecommendations", clothingRecommendations);
        }
        templateParams.put("windyDays", windyDays);
        templateParams.put("weathers", weathers);
        templateParams.put("weatherDescriptions", weatherDescriptions);
        templateParams.put("numberOfDays", weathers.size());
        templateParams.put("daysOfWeek", daysOfWeek);

        mailService.sendMessageUsingThymeleafTemplate(userPreferences.getUser().getEmail(), "Weather Forecast", templatePath, templateParams);
    }

    private List<LocalDateTime> getDatesFromWeatherList(List<DailyWeather> weathers) {
        return weathers.stream()
                .map(weather -> new Timestamp(weather.getDt() * 1000))
                .map(Timestamp::toLocalDateTime).toList();
    }

    private List<Boolean> getWindyDays(List<DailyWeather> weathers) {
        List<Boolean> windyDays = new ArrayList<>();
        for (DailyWeather weather : weathers) {
            windyDays.add(weather.getWindSpeed() > TEN_METERS_PER_SECOND);
        }
        return windyDays;
    }

    private String getTemplateName(MailType mailType) {
        return switch (mailType) {
            case DAILY -> "daily-mail-template.html";
            case WEEKLY -> "weekly-mail-template.html";
            case WEEKENDS -> "weekendly-mail-template.html";
        };
    }

    private List<DailyWeather> getWeatherSublist(DailyWeather[] weathers, MailType mailType) {
        List<DailyWeather> weatherSublist = new ArrayList<>();
        if (mailType == MailType.DAILY) {
            weatherSublist.add(weathers[0]);
        }
        else if (mailType == MailType.WEEKLY) {
            Collections.addAll(weatherSublist, weathers);
        }
        else {
            var howManyForecasts = switch(LocalDate.now().getDayOfWeek()) {
                case FRIDAY -> 3;
                case SATURDAY -> 2;
                default -> 1;
            };
            weatherSublist.addAll(Arrays.asList(weathers).subList(0, howManyForecasts));
        }
        return weatherSublist;
    }


}
