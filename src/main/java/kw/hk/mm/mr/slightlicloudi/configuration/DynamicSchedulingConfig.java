package kw.hk.mm.mr.slightlicloudi.configuration;

import kw.hk.mm.mr.slightlicloudi.mailing.MailType;
import kw.hk.mm.mr.slightlicloudi.user.UserPreferences;
import kw.hk.mm.mr.slightlicloudi.user.UserPreferencesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
@EnableScheduling
public class DynamicSchedulingConfig implements SchedulingConfigurer {

    UserPreferencesRepository userPreferencesRepository;

    private Map<MailType, String> getCronTriggerFromUserPreferences(UserPreferences userPreferences) {
        Map<MailType, String> userCrons = new HashMap<>();

        if(userPreferences.isReceivingDaily()) {
            userCrons.put(MailType.DAILY, userPreferences.getDailyReceivingTime().getMinute() + " " +
                    userPreferences.getDailyReceivingTime().getHour() + " * * *");
        }
        if(userPreferences.isReceivingWeekly()) {
            userCrons.put(MailType.WEEKLY, userPreferences.getWeeklyReceivingTime().getMinute() + " " +
                    userPreferences.getWeeklyReceivingTime().getHour() + " * * " +
                    userPreferences.getWeeklyReceivingWeekday().getValue());
        }
        if(userPreferences.isReceivingWeekends()) {
            userCrons.put(MailType.WEEKENDS, userPreferences.getWeekendsReceivingTime().getMinute() + " " +
                    userPreferences.getWeekendsReceivingTime().getHour() + " * * " +
                    userPreferences.getWeekendsReceivingWeekday().getValue());
        }

        return userCrons;
    }


    @Bean
    public Executor taskExecutor() {
        return Executors.newSingleThreadScheduledExecutor();
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        var preferences = userPreferencesRepository.findAll();
        taskRegistrar.setScheduler(taskExecutor());

        for(var preference : preferences) {
            var crons = getCronTriggerFromUserPreferences(preference);

            for(var cron : crons.entrySet()) {
                taskRegistrar.addTriggerTask(
                        new Runnable() {
                            @Override
                            public void run() {

                            }
                        },
                        new CronTrigger(cron.getValue())
                );
            }
        }

    }

}