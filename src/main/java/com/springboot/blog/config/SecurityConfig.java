package com.springboot.blog.config;

import com.springboot.blog.Security.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password4j.BcryptPassword4jPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    // In springboot version 5.0 version onwards We dont need to pass the userdetails service and
    // password encoder to the authentication manager, it will automatically do it.
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BcryptPassword4jPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws AccessDeniedException {
        http.csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated())
                .authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers(HttpMethod.GET, "/api/**").permitAll()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }


// creating In memory user based authentication with roles.
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails ramesh = User.builder()
//                .username("ramesh")
//                .password(passwordEncoder().encode("ramesh"))
//                .roles("USER")
//                .build();
//        UserDetails admin = User.builder()
//                .username("ADMIN")
//                .password(passwordEncoder().encode("ADMIN"))
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(ramesh, admin);
//    }


}
