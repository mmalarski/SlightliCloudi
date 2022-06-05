package kw.hk.mm.mr.slightlicloudi.mailing.scheduling;

import kw.hk.mm.mr.slightlicloudi.mailing.MailService;
import kw.hk.mm.mr.slightlicloudi.mailing.MailType;
import kw.hk.mm.mr.slightlicloudi.user.UserPreferences;
import kw.hk.mm.mr.slightlicloudi.weather.WeatherService;
import kw.hk.mm.mr.slightlicloudi.weather.mapping.DailyWeather;
import kw.hk.mm.mr.slightlicloudi.weather.mapping.Weather;
import kw.hk.mm.mr.slightlicloudi.weather.mapping.WeatherResponse;
import kw.hk.mm.mr.slightlicloudi.weather.recommendations.ClothingRecommendations;
import kw.hk.mm.mr.slightlicloudi.weather.recommendations.WeatherConditions;

import javax.mail.MessagingException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class MailSchedule {

    MailService mailService;
    WeatherConditions weatherConditions;
    WeatherService weatherService;


    public void sendMail(UserPreferences userPreferences, MailType mailType) throws MessagingException {
        String templatePath = "";
        Map<String, Object> templateModel = new HashMap<>();

        WeatherResponse weatherResponse = weatherService.getWeather(userPreferences.getLatitude(), userPreferences.getLongitude());
        List<DailyWeather> weathers = new ArrayList<>();

        if(mailType == MailType.DAILY) {
            weathers.add(weatherResponse.getDaily()[0]);
            templateModel.put("forecastType", "Daily");
            templatePath = "daily-mail-template.html";
        }
        else if(mailType == MailType.WEEKLY) {
            Collections.addAll(weathers, weatherResponse.getDaily());
            templateModel.put("forecastType", "Weekly");
            templatePath = "weekly-mail-template.html";
        }
        else {
            int howManyForecasts = 0;
            switch(LocalDate.now().getDayOfWeek()) {
                case FRIDAY -> howManyForecasts = 3;
                case SATURDAY -> howManyForecasts = 2;
                case SUNDAY -> howManyForecasts = 1;
            }
            templateModel.put("forecastType", "Weekend");
            weathers.addAll(Arrays.asList(weatherResponse.getDaily()).subList(0, howManyForecasts));
            templatePath = "weekendly-mail-template.html";
        }

        List<ClothingRecommendations> clothingRecommendations = weathers.stream()
                .map(weather -> weatherConditions.getClothingRecommendations(weather)).toList();

        for(int i = 0; i < clothingRecommendations.size(); i++) {
            if(weathers.get(i).getWindSpeed() > 35) {
                templateModel.put("weatherRecommendations", clothingRecommendations.get(i).getClothesList());
                templateModel.put("windRecommendations", clothingRecommendations.get(i).getWindyClothesList());
                templateModel.put("temperatures", weathers.get(i).getTemp().getDay());
            } else {
                templateModel.put("temperatures", weathers.get(i).getTemp().getDay());
                templateModel.put("weatherRecommendations", clothingRecommendations.get(i).getClothesList());
            }
        }

        templateModel.put("weathers", weathers);
        templateModel.put("numberOfDays", weathers.size());

        mailService.sendMessageUsingThymeleafTemplate(userPreferences.getUser().toString(), "Weather Forecast", templatePath, templateModel);
    }


}
