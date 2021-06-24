package com.example.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.example.demo.configuration.ApplicationUserRole.ADMIN;
import static com.example.demo.configuration.ApplicationUserRole.STUDENT;

@Configuration
@EnableWebSecurity
public class Security  extends WebSecurityConfigurerAdapter {

    PasswordEncoder encoder;

    public Security(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/","/css/*","/js/*").permitAll()
                .antMatchers("/api").hasRole(STUDENT.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        UserDetails uday = User
                                .builder()
                                .username("uday")
                                .password(encoder.encode("password"))
                                .roles(STUDENT.name())
                                .build();
        UserDetails hima = User
                                .builder()
                                .username("uday")
                                .password(encoder.encode("password"))
                                .roles(ADMIN.name())
                                .build();
        return new InMemoryUserDetailsManager(uday);
    }
}
