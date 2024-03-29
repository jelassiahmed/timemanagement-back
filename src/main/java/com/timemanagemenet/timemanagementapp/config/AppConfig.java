package com.timemanagemenet.timemanagementapp.config;

import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public KeycloakConfigResolver keycloakconfigResolver() {
        return new KeycloakSpringBootConfigResolver();
    }

}
