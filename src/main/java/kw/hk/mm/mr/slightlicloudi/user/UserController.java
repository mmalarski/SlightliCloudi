package kw.hk.mm.mr.slightlicloudi.user;

import kw.hk.mm.mr.slightlicloudi.configuration.JWT.JWTHandler;
import kw.hk.mm.mr.slightlicloudi.weather.WeatherService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("user")
@AllArgsConstructor
public class UserController {
    final UserRepository userRepository;
    final AuthenticationManager authenticationManager;
    final JWTHandler tokenHandler;
    final PasswordEncoder passwordEncoder;
    final WeatherService weatherService;

    @GetMapping("/ping")
    public String pong() {
        weatherService.getWeather("33.44", "-94.04");
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

}
