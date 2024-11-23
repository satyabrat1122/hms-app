package com.hms.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
public class SecurityConfig{
    private JWTFilter jwtFilter;
    public SecurityConfig(JWTFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }
    @Bean
    public SecurityFilterChain securityFilterChain (
            HttpSecurity http
    ) throws Exception{
        //hcd2
        http.csrf().disable().cors().disable();
        http.addFilterBefore(jwtFilter, AuthorizationFilter.class);
        //haap
     http.authorizeHttpRequests().anyRequest().permitAll();
//       http.authorizeHttpRequests().
//               requestMatchers("/api/v1/users/signup","/api/v1/users/signup-property-owner","/api/v1/users/login").
//                permitAll().requestMatchers("/api/v1").
//                hasAnyRole("OWNER").anyRequest().authenticated();
        return http.build();
    }
}
