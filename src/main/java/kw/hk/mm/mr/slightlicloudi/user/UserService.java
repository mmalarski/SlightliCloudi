package kw.hk.mm.mr.slightlicloudi.user;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {
    final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = userRepository
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Could not find user " + email));
        return new User(email, user.getPassword(), Collections.emptyList());
    }
}
