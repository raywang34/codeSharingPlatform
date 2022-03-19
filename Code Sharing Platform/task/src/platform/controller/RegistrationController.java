package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import platform.model.User;
import platform.repository.UserRepository;
import platform.service.UserDetailsServiceImpl;

@RestController
public class RegistrationController {
    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/register")
    public void register(@RequestBody User user) {
        // input validation omitted for brevity

        user.setPassword(encoder.encode(user.getPassword()));

        userDetailsService.save(user);
    }
}
