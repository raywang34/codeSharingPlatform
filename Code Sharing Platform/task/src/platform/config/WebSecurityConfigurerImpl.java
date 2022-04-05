package platform.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfigurerImpl extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/h2-console/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/test").hasAnyRole("USER")
                .mvcMatchers("/code/new").hasRole("USER")
                .mvcMatchers("/api/code/latest").hasAnyRole("ADMIN", "USER")
                .mvcMatchers("/code/**").permitAll()
                .mvcMatchers("/register").permitAll()
                .mvcMatchers("/**").authenticated() // or .anyRequest().authenticated() // To access any URI (anyRequest()) on our app, a user needs to authenticate (authenticated()).
                .and()
                .formLogin() // Enables form-based auth with default settings.
                .and()
                .httpBasic() // Enables HTTP Basic auth.
                .and()
                .csrf().disable(); // disabling CSRF will allow sending POST request using Postman
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService) // user store 1
                .passwordEncoder(getEncoder());

        auth
                .inMemoryAuthentication()
                .withUser("A").password("pass1").roles()
                .and()
                .withUser("B").password("pass2").roles()
                .and()
                .withUser("Admin").password("hardcoded").roles("USER")
                .and()
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
//                .withUser("user1").password(getEncoder().encode("pass1")).roles()
//                .and()
//                .withUser("user2").password(getEncoder().encode("pass2")).roles("USER")
//                .and()
//                .withUser("user3").password(getEncoder().encode("pass3")).roles("ADMIN")
//                .and()
//                .passwordEncoder(getEncoder()); // specifying what encoder we used;
    }

    // creating a PasswordEncoder that is needed in two places
    @Bean
    public PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }
}
