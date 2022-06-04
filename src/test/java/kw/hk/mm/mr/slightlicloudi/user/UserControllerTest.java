package kw.hk.mm.mr.slightlicloudi.user;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import kw.hk.mm.mr.slightlicloudi.configuration.BeanRegistrationContextInitializer;
import kw.hk.mm.mr.slightlicloudi.configuration.JWT.JWTHandler;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestConstructor;
import org.springframework.web.context.WebApplicationContext;

import java.time.DayOfWeek;
import java.time.LocalTime;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.equalTo;


@SpringBootTest
@Profile("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ContextConfiguration(initializers = BeanRegistrationContextInitializer.class)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@AllArgsConstructor
class UserControllerTest {

    WebApplicationContext webApplicationContext;

    UserRepository userRepository;
    UserPreferencesRepository userPreferencesRepository;
    PasswordEncoder passwordEncoder;
    JWTHandler tokenHandler;

    @BeforeAll
    void setUp() {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
        UserPreferences preferences = setupUserPreferences();
        User user = setupUser();
        preferences.setUser(user);
        userPreferencesRepository.save(preferences);
        user.setPreferences(preferences);
        userRepository.save(user);
    }

    private User setupUser() {
        User user = new User();
        user.setId(1);
        user.setEmail("foo@bar.com");
        user.setPassword(passwordEncoder.encode("testPassword"));
        return user;
    }

    private UserPreferences setupUserPreferences() {
        UserPreferences preferences = new UserPreferences();
        preferences.setId(1);
        preferences.setClothingRecommendation(true);
        preferences.setDailyReceivingTime(LocalTime.of(10, 0));
        preferences.setReceivingDaily(true);
        preferences.setReceivingWeekends(true);
        preferences.setWeekendsReceivingTime(LocalTime.of(10, 0));
        preferences.setWeekendsReceivingWeekday(DayOfWeek.SATURDAY);
        preferences.setReceivingWeekly(true);
        preferences.setWeeklyReceivingTime(LocalTime.of(10, 0));
        preferences.setWeeklyReceivingWeekday(DayOfWeek.MONDAY);
        preferences.setLatitude(1.0);
        preferences.setLongitude(1.0);
        return preferences;
    }

    @Test
    void successfulRegisterUser() {
        given().
                contentType("application/json")
                .body("{\n" +
                        "    \"email\": \"foo2@bar.com\",\n" +
                        "    \"password\": \"testPassword\"\n" +
                        "}").
        when().
                post("/user/register").
        then().
                statusCode(201)
                .body(equalTo("foo2@bar.com"));
    }

    @Test
    void registerUserExistingEmail() {
        given().
                contentType("application/json")
                .body("{\n" +
                        "    \"email\": \"foo@bar.com\",\n" +
                        "    \"password\": \"testPassword\"\n" +
                        "}").
        when().
                post("/user/register").
        then().
                statusCode(400)
                .body(equalTo("Invalid email"));
    }

    @Test
    void loginUser() {
        given().
                contentType("application/json")
                .body("{\n" +
                        "    \"email\": \"foo@bar.com\",\n" +
                        "    \"password\": \"testPassword\"\n" +
                        "}").
        when().
                post("/user/login").
        then().
                statusCode(200)
                .body(equalTo("foo@bar.com"));
    }

    @Test
    void loginUserInvalidCredentials() {
        given().
                contentType("application/json")
                .body("{\n" +
                        "    \"email\": \"foo@bar.com\",\n" +
                        "    \"password\": \"wrongPassword\"\n" +
                        "}").
        when().
                post("/user/login").
        then().
                statusCode(401);
    }
    @Test
    void getUserPreferences() {
        var preferences = userPreferencesRepository.findByUserId(1L).orElseThrow(() -> new IllegalArgumentException("No preferences found"));
        given().
                contentType("application/json")
                .header("Authorization", "Bearer " + tokenHandler.generateToken("foo@bar.com")).
        when().
                get("/user/preferences").
        then().
                statusCode(200)
                .body("id", equalTo((int) preferences.getId()),
                        "clothingRecommendation", equalTo(true),
                        "dailyReceivingTime", equalTo("10:00:00"),
                        "receivingDaily", equalTo(true),
                        "receivingWeekends", equalTo(true),
                        "weekendsReceivingTime", equalTo("10:00:00"),
                        "weekendsReceivingWeekday", equalTo("SATURDAY"),
                        "receivingWeekly", equalTo(true),
                        "weeklyReceivingTime", equalTo("10:00:00"),
                        "weeklyReceivingWeekday", equalTo("MONDAY"),
                        "latitude", equalTo(1.0f),
                        "longitude", equalTo(1.0f));
    }

    @Test
    void updateUserPreferences() {
        given().
                contentType("application/json")
                .header("Authorization", "Bearer " + tokenHandler.generateToken("foo@bar.com")).
                contentType("application/json").
                body("{\n" +
                        "    \"clothingRecommendation\": false,\n" +
                        "    \"dailyReceivingTime\": \"10:00:00\",\n" +
                        "    \"receivingDaily\": false,\n" +
                        "    \"receivingWeekends\": true,\n" +
                        "    \"weekendsReceivingTime\": \"15:00:00\",\n" +
                        "    \"weekendsReceivingWeekday\": \"FRIDAY\",\n" +
                        "    \"receivingWeekly\": false,\n" +
                        "    \"weeklyReceivingTime\": \"10:00:00\",\n" +
                        "    \"weeklyReceivingWeekday\": \"MONDAY\",\n" +
                        "    \"latitude\": 30.0,\n" +
                        "    \"longitude\": 20.0\n" +
                        "}").
        when().
                patch("/user/preferences").
        then().
                statusCode(200);
    }

}