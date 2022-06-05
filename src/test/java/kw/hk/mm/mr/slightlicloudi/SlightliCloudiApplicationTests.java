package kw.hk.mm.mr.slightlicloudi;

import kw.hk.mm.mr.slightlicloudi.configuration.BeanRegistrationContextInitializer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@Profile("test")
@ContextConfiguration(initializers = BeanRegistrationContextInitializer.class)
class SlightliCloudiApplicationTests {

    @Test
    void contextLoads() {
    }

}
