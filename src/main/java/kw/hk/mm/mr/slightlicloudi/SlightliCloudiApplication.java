package kw.hk.mm.mr.slightlicloudi;

import kw.hk.mm.mr.slightlicloudi.configuration.BeanRegistrationContextInitializer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class SlightliCloudiApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(SlightliCloudiApplication.class)
                .initializers(new BeanRegistrationContextInitializer())
                .run(args);
    }

}
