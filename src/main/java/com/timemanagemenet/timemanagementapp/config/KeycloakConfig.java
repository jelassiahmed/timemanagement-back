package com.timemanagemenet.timemanagementapp.config;

import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springboot.KeycloakSpringBootProperties;
import org.keycloak.adapters.springsecurity.KeycloakSecurityComponents;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;

import java.util.Map;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class KeycloakConfig {

    @Autowired
    private KeycloakSpringBootProperties keycloakProperties;

    @Bean
    public KeycloakSpringBootConfigResolver keycloakConfigResolver() {
        return new KeycloakSpringBootConfigResolver();
    }

    @Bean
    public Keycloak keycloak() {
        return KeycloakBuilder.builder()
                .serverUrl("http://localhost:8180")
                .realm("TimeManagement")
                .clientId("timemanagement-back")
                .clientSecret("**********")
                .username("admin_user")
                .password("admin")
                .build();
    }

    private String getClientSecret() {
        Map<String, Object> credentials = keycloakProperties.getCredentials();
        if (credentials != null && credentials.containsKey("secret")) {
            return credentials.get("secret").toString();
        }
        return null;
    }

    @Bean
    public SimpleAuthorityMapper simpleAuthorityMapper() {
        return new SimpleAuthorityMapper();
    }
}
