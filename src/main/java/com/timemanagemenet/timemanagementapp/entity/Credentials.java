package com.timemanagemenet.timemanagementapp.entity;

import org.keycloak.representations.idm.CredentialRepresentation;

public class Credentials {

    private Credentials() {
        // Private constructor to prevent instantiation
    }
    public static CredentialRepresentation createPasswordCredentials(String password) {
        CredentialRepresentation passwordCredentials = new CredentialRepresentation();
        passwordCredentials.setTemporary(false);
        passwordCredentials.setType(CredentialRepresentation.PASSWORD);
        passwordCredentials.setValue(password);
        return passwordCredentials;
    }
}