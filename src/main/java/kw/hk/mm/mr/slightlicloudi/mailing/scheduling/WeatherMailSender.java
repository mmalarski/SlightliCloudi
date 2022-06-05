package kw.hk.mm.mr.slightlicloudi.mailing.scheduling;

import kw.hk.mm.mr.slightlicloudi.mailing.MailService;
import kw.hk.mm.mr.slightlicloudi.mailing.MailType;
import kw.hk.mm.mr.slightlicloudi.user.UserPreferences;
import kw.hk.mm.mr.slightlicloudi.weather.WeatherService;
import kw.hk.mm.mr.slightlicloudi.weather.mapping.DailyWeather;
import kw.hk.mm.mr.slightlicloudi.weather.mapping.WeatherResponse;
import kw.hk.mm.mr.slightlicloudi.weather.recommendations.WeatherConditions;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.mail.MessagingException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.*;

@AllArgsConstructor
@Slf4j
public class WeatherMailSender {

    private static final int WIND_SPEED_THRESHOLD = 10; // speed in m/s
    MailService mailService;
    WeatherConditions weatherConditions;
    WeatherService weatherService;


    public void sendMail(UserPreferences userPreferences, MailType mailType) throws MessagingException {
        String templatePath;
        Map<String, Object> templateModel = new HashMap<>();
        WeatherResponse weatherResponse = weatherService.getWeather(userPreferences.getLatitude(), userPreferences.getLongitude());
        List<DailyWeather> weathers = new ArrayList<>();
        List<Integer> windyDays = new ArrayList<>();

        if (mailType == MailType.DAILY) {
            weathers.add(weatherResponse.getDaily()[0]);
            templatePath = "daily-mail-template.html";
        }
        else if (mailType == MailType.WEEKLY) {
            Collections.addAll(weathers, weatherResponse.getDaily());
            templatePath = "weekly-mail-template.html";
        }
        else {
            var howManyForecasts = switch(LocalDate.now().getDayOfWeek()) {
                case FRIDAY -> 3;
                case SATURDAY -> 2;
                default -> 1;
            };
            weathers.addAll(Arrays.asList(weatherResponse.getDaily()).subList(0, howManyForecasts));
            templatePath = "weekendly-mail-template.html";
        }
        var daysOfWeek = weathers.stream()
                .map(weather -> new Timestamp(weather.getDt() * 1000))
                .map(Timestamp::toLocalDateTime).toList();
        var clothingRecommendations = weathers.stream()
                .map(weather -> weatherConditions.getClothingRecommendations(weather)).toList();
        var weatherDescriptions = weathers.stream().map(weather -> weatherConditions.getWeatherDescription(weather)).toList();
        for (int i = 0; i < clothingRecommendations.size(); i++) {
            if (weathers.get(i).getWindSpeed() > WIND_SPEED_THRESHOLD) {
                windyDays.add(i);
            }
        }
        if (userPreferences.isClothingRecommendation()) {
            templateModel.put("weatherRecommendations", clothingRecommendations);
        }
        templateModel.put("windyDays", windyDays);
        templateModel.put("weathers", weathers);
        templateModel.put("weatherDescriptions", weatherDescriptions);
        templateModel.put("numberOfDays", weathers.size());
        templateModel.put("daysOfWeek", daysOfWeek);

        mailService.sendMessageUsingThymeleafTemplate(userPreferences.getUser().getEmail(), "Weather Forecast", templatePath, templateModel);
    }


}
