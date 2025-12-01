package com.springboot.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password4j.BcryptPassword4jPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BcryptPassword4jPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws AccessDeniedException {
        http.csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated())
                .authorizeHttpRequests((authorize)->
                        authorize.requestMatchers(HttpMethod.GET,"/api/**").permitAll()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
    // creating In memory user based authentication with roles.
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails ramesh = User.builder()
                .username("ramesh")
                .password(passwordEncoder().encode("ramesh"))
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("ADMIN")
                .password(passwordEncoder().encode("ADMIN"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(ramesh, admin);
    }


}
