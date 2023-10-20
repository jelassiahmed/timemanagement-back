package com.timemanagemenet.timemanagementapp.Service;

import com.timemanagemenet.timemanagementapp.Entity.BaseEntity;
import org.keycloak.KeycloakPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;

public class BaseService {
    public static void initializeEntity(BaseEntity entity) {
        LocalDateTime now = LocalDateTime.now();
        entity.setCreatedAt(now);
        entity.setUpdatedAt(now);
        entity.setIsDeleted(0);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof KeycloakPrincipal<?> keycloakPrincipal) {
            String createdBy = keycloakPrincipal.getName();
            entity.setCreatedBy(createdBy);
            entity.setUpdatedBy(createdBy);
        }
    }
    public static void updateEntity(BaseEntity existingEntity, BaseEntity updatedEntity) {
        existingEntity.setUpdatedAt(LocalDateTime.now());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof KeycloakPrincipal) {
            String updatedBy = ((KeycloakPrincipal<?>) authentication.getPrincipal()).getName();
            existingEntity.setUpdatedBy(updatedBy);
        }
    }
}
