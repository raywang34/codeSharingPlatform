package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
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

//    @GetMapping("/username")
//    public void username(Authentication auth) {
//        System.out.println(auth.getName());
//    }

    @GetMapping("/username")
    public void username(@AuthenticationPrincipal UserDetails details) {
        System.out.println(details.getUsername());
    }

    @GetMapping("/details")
    public void details(Authentication auth) {
        UserDetails details = (UserDetails) auth.getPrincipal();

        System.out.println("Username: " + details.getUsername());
        System.out.println("Password: " + details.getPassword());
        System.out.println("User has authorities/roles: " + details.getAuthorities());
    }
}
