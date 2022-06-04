package kw.hk.mm.mr.slightlicloudi.user;

import kw.hk.mm.mr.slightlicloudi.configuration.BeanRegistrationContextInitializer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Profile("test")
@ContextConfiguration(initializers = BeanRegistrationContextInitializer.class)
class UserControllerTest {

    @Test
    void registerUser() {
    }

    @Test
    void loginUser() {
    }

    @Test
    void setUserPreferences() {
    }
}