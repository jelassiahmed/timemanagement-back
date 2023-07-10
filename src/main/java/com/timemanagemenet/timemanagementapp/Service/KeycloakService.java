package com.timemanagemenet.timemanagementapp.Service;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeycloakService {
    private final Keycloak keycloak;

    @Autowired
    public KeycloakService(Keycloak keycloak) {
        this.keycloak = keycloak;
    }

    public String getUserIdByUsername(String username) {
        try {
            RealmResource realmResource = keycloak.realm("your-realm");
            UsersResource usersResource = realmResource.users();
            List<UserRepresentation> users = usersResource.search(username);
            if (!users.isEmpty()) {
                return users.get(0).getId();
            }
        } catch (Exception e) {
            // Handle any exceptions
        }
        return null;
    }
}
