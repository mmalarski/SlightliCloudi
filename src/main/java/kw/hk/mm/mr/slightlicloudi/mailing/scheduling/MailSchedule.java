package kw.hk.mm.mr.slightlicloudi.mailing.scheduling;

import kw.hk.mm.mr.slightlicloudi.mailing.MailService;
import kw.hk.mm.mr.slightlicloudi.mailing.MailType;
import kw.hk.mm.mr.slightlicloudi.user.UserPreferences;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.Map;

public class MailSchedule {

    MailService mailService;

    public void sendMail(UserPreferences userPreferences, MailType mailType) throws MessagingException {
        String templatePath = "";
        Map<String, Object> templateModel = new HashMap<>();
        if(mailType == MailType.DAILY) {
            templatePath = "daily-mail-template.html";
        }
        if(mailType == MailType.WEEKLY) {
            templatePath = "weekly-mail-template.html";
        }
        if(mailType == MailType.WEEKENDS) {
            templatePath = "weekendly-mail-template.html";
        }

        mailService.sendMessageUsingThymeleafTemplate(userPreferences.getUser().toString(), "Weather Forecast", templatePath, templateModel);
    }


}
