package kw.hk.mm.mr.slightlicloudi.user;

import kw.hk.mm.mr.slightlicloudi.configuration.JWT.JWTHandler;
import kw.hk.mm.mr.slightlicloudi.mailing.MailService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
@AllArgsConstructor
public class UserController {
    final UserRepository userRepository;
    final AuthenticationManager authenticationManager;
    final JWTHandler tokenHandler;
    final PasswordEncoder passwordEncoder;
    final MailService mailService;

    @GetMapping("/ping")
    public String pong() {
        return "Pong";
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public User getUser(@PathVariable long id) {
        return userRepository.getById(id);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody User newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        var user = userRepository.findByEmail(newUser.getEmail());
        if (user.isEmpty()) {
            var registeredUser = userRepository.save(newUser);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .header(
                            HttpHeaders.AUTHORIZATION,
                            tokenHandler.generateToken(registeredUser.getEmail())
                    )
                    .body(registeredUser.getEmail());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid email");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@Valid @RequestBody User request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(), request.getPassword()
                    )
            );
            return ResponseEntity.ok()
                    .header(
                            HttpHeaders.AUTHORIZATION,
                            tokenHandler.generateToken(request.getEmail())
                    )
                    .body(request.getEmail());
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/send-email")
    public String sendEmail() throws MessagingException {
        Map<String, Object> params = new HashMap<>();
        List<String> recommendations = new ArrayList<>();
        recommendations.add("It's going to be a sunny day");
        recommendations.add("Take a coat");
        recommendations.add("Take an umbrella");
        params.put("forecastType", "My own");
        params.put("temperature", "24");
        params.put("windRecommendations", recommendations);
        mailService.sendMessageUsingThymeleafTemplate("mihau.malarski@gmail.com", "subject", params);
        return "Done! Email sent!";
    }
}
