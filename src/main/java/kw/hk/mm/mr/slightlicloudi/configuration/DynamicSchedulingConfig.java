package kw.hk.mm.mr.slightlicloudi.configuration;

import kw.hk.mm.mr.slightlicloudi.mailing.MailType;
import kw.hk.mm.mr.slightlicloudi.mailing.scheduling.WeatherMailSender;
import kw.hk.mm.mr.slightlicloudi.user.UserPreferences;
import kw.hk.mm.mr.slightlicloudi.user.UserPreferencesRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import javax.mail.MessagingException;
import java.util.*;
import java.util.concurrent.Executors;

@Configuration
@EnableScheduling
@AllArgsConstructor
@Slf4j
public class DynamicSchedulingConfig implements SchedulingConfigurer {

    private final UserPreferencesRepository userPreferencesRepository;
    private final WeatherMailSender weatherMailSender;

    private Map<MailType, String> getCronTriggerFromUserPreferences(UserPreferences userPreferences) {
        Map<MailType, String> userCrons = new EnumMap<>(MailType.class);

        if(userPreferences.isReceivingDaily()) {
            userCrons.put(MailType.DAILY, "0 " + userPreferences.getDailyReceivingTime().getMinute() + " " +
                    userPreferences.getDailyReceivingTime().getHour() + " * * *");
        }
        if(userPreferences.isReceivingWeekly()) {
            userCrons.put(MailType.WEEKLY, "0 " + userPreferences.getWeeklyReceivingTime().getMinute() + " " +
                    userPreferences.getWeeklyReceivingTime().getHour() + " * * " +
                    userPreferences.getWeeklyReceivingWeekday().getValue());
        }
        if(userPreferences.isReceivingWeekends()) {
            userCrons.put(MailType.WEEKENDS, "0 " + userPreferences.getWeekendsReceivingTime().getMinute() + " " +
                    userPreferences.getWeekendsReceivingTime().getHour() + " * * " +
                    userPreferences.getWeekendsReceivingWeekday().getValue());
        }

        return userCrons;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        var preferences = userPreferencesRepository.findAll();
        taskRegistrar.setScheduler(Executors.newSingleThreadScheduledExecutor());

        for(var preference : preferences) {
            var crons = getCronTriggerFromUserPreferences(preference);

            for(var cron : crons.entrySet()) {
                taskRegistrar.addTriggerTask(
                        () -> {
                            try {
                                weatherMailSender.sendMail(preference, cron.getKey());
                            } catch (MessagingException e) {
                                log.error("Error sending mail to " + preference.getUser().getEmail(), e);
                            }
                        },
                        new CronTrigger(cron.getValue())
                );
            }
        }

    }

}