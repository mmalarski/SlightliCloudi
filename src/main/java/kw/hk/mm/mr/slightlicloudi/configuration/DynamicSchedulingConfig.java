package kw.hk.mm.mr.slightlicloudi.configuration;

import kw.hk.mm.mr.slightlicloudi.mailing.scheduling.MailScheduler;
import kw.hk.mm.mr.slightlicloudi.user.UserPreferencesRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class DynamicSchedulingConfig {

    private final UserPreferencesRepository userPreferencesRepository;
    private final MailScheduler mailScheduler;

    public DynamicSchedulingConfig(UserPreferencesRepository userPreferencesRepository, MailScheduler mailScheduler) {
        this.userPreferencesRepository = userPreferencesRepository;
        this.mailScheduler = mailScheduler;
        var preferences = this.userPreferencesRepository.findAll();
        for (var preference : preferences) {
            this.mailScheduler.scheduleMailsFromPreferences(preference);
        }
    }
}