package platform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfigurerImpl extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/h2-console/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/code/new").hasRole("ADMIN")
                .mvcMatchers("/api/code/latest").hasAnyRole("ADMIN", "USER")
                .mvcMatchers("/code/**").permitAll()
                .mvcMatchers("/**").authenticated() // or .anyRequest().authenticated() // To access any URI (anyRequest()) on our app, a user needs to authenticate (authenticated()).
                .and()
                .formLogin() // Enables form-based auth with default settings.
                .and()
                .httpBasic() // Enables HTTP Basic auth.
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user1").password(getEncoder().encode("pass1")).roles()
                .and()
                .withUser("user2").password(getEncoder().encode("pass2")).roles("USER")
                .and()
                .withUser("user3").password(getEncoder().encode("pass3")).roles("ADMIN")
                .and()
                .passwordEncoder(getEncoder()); // specifying what encoder we used;
    }

    // creating a PasswordEncoder that is needed in two places
    @Bean
    public PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }
}
