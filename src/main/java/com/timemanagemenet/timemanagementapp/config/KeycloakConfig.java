package com.timemanagemenet.timemanagementapp.config;

import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springboot.KeycloakSpringBootProperties;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
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
                .serverUrl(keycloakProperties.getAuthServerUrl())
                .realm(keycloakProperties.getRealm())
                .clientId(keycloakProperties.getResource())
                .clientSecret(getClientSecret())
                .username(keycloakProperties.getPrincipalAttribute())
                .password(keycloakProperties.getCredentials().get("secret").toString()) // Use 'secret' instead of 'password'
                .build();
    }


    private String getClientSecret() {
        Map<String, Object> credentials = keycloakProperties.getCredentials();
        if (credentials != null && credentials.containsKey("secret")) {
            return credentials.get("secret").toString();
        }
        return null;
    }


}
