package com.springboot.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
    // In basic Authentication we need to pass the username and password in headers of the requests.

    // creating In memory user based authentication with roles.
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails ramesh = User.builder().username("ramesh").password("ramesh").roles("USER").build();
        UserDetails admin = User.builder().username("ADMIN").password("ADMIN").roles("ADMIN").build();
        return new InMemoryUserDetailsManager(ramesh,admin);
    }


}
