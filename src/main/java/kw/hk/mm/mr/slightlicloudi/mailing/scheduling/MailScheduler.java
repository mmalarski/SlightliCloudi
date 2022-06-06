package kw.hk.mm.mr.slightlicloudi.mailing.scheduling;

import kw.hk.mm.mr.slightlicloudi.mailing.MailType;
import kw.hk.mm.mr.slightlicloudi.user.UserPreferences;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import javax.mail.MessagingException;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

@Slf4j
@EnableScheduling
public class MailScheduler extends ThreadPoolTaskScheduler {
        private final WeatherMailSender weatherMailSender;
        private final Map<String, ScheduledFuture<?>> cronTaskMap = new HashMap<>();
        public MailScheduler(WeatherMailSender weatherMailSender) {
            super();
            this.weatherMailSender = weatherMailSender;
            setPoolSize(5);
            setRemoveOnCancelPolicy(true);
        }

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

        public void scheduleMailsFromPreferences(UserPreferences userPreferences) {
            var crons = getCronTriggerFromUserPreferences(userPreferences);
            for (var cron : crons.entrySet()) {
                cronTaskMap.put(userPreferences.getUser().getEmail() + cron.getKey().toString(), schedule(() -> {
                    try {
                        weatherMailSender.sendWeatherMail(userPreferences, cron.getKey());
                    } catch (MessagingException e) {
                        log.error("Error sending mail to " + userPreferences.getUser().getEmail(), e);
                    }
                }, new CronTrigger(cron.getValue())));
            }
        }

        public void cancelMailsFromPreferences(String userMail) {
            for (var mailTypes : MailType.values()) {
                var key = userMail + mailTypes.toString();
                if (cronTaskMap.containsKey(key)) {
                    cronTaskMap.get(key).cancel(true);
                    cronTaskMap.remove(key);
                }
            }
        }
}
