package kw.hk.mm.mr.slightlicloudi.configuration;

import kw.hk.mm.mr.slightlicloudi.mailing.scheduling.MailScheduler;
import kw.hk.mm.mr.slightlicloudi.user.UserPreferencesRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;


// Think if this clean to keep it this way,
// we can either keep it here or move to MailScheduler constructor
// or just keep it as a config class but without this SchedulingConfigurer bs,
// cause that's probably unnecessary.
@Configuration
@EnableScheduling
@AllArgsConstructor
@Slf4j
public class DynamicSchedulingConfig implements SchedulingConfigurer {

    private final UserPreferencesRepository userPreferencesRepository;
    private final MailScheduler mailScheduler;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        var preferences = userPreferencesRepository.findAll();
        for (var preference : preferences) {
            mailScheduler.scheduleMailsFromPreferences(preference);
        }

    }

}