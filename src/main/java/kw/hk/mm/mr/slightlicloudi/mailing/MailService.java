package kw.hk.mm.mr.slightlicloudi.mailing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Map;
@Service
public class MailService {

    private final JavaMailSender javaMailSender;
    @Autowired
    private SpringTemplateEngine thymeleafTemplateEngine;

    @Autowired
    public MailService (JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendMessageUsingThymeleafTemplate(
            String to, String subject, String templatePath, Map<String, Object> templateModel) throws MessagingException {

        Context thymeleafContext = new Context();
        thymeleafContext.setVariables(templateModel);
        String htmlBody = thymeleafTemplateEngine.process(templatePath, thymeleafContext);

        sendHtmlMessage(to, subject, htmlBody);
    }

    private void sendHtmlMessage(String to,
                         String subject,
                         String text) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);

        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(text, true);

        javaMailSender.send(message);
    }
}
