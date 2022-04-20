package kw.hk.mm.mr.slightlicloudi.user;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("user")
@AllArgsConstructor
public class UserController {
    final UserRepository userRepository;

    @GetMapping("/ping")
    public String pong() {
        return "Pong";
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public User getUser(@PathVariable long id) {
        return userRepository.getById(id);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(@Valid @RequestBody User newUser) {
        userRepository.save(newUser);
    }

}
