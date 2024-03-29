package com.timemanagemenet.timemanagementapp.service.reclamation;

import com.timemanagemenet.timemanagementapp.entity.Reclamation;
import com.timemanagemenet.timemanagementapp.repository.ReclamationRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReclamationServiceImp implements ReclamationService{
    private final ReclamationRepository reclamationRepository;

    public ReclamationServiceImp(ReclamationRepository reclamationRepository) {
        this.reclamationRepository = reclamationRepository;
    }


    @Override
    public List<Reclamation> getAllReclamations() {
        return reclamationRepository.findAll();
    }

    @Override
    public Reclamation getReclamationById(Long reclamationId) {
        return reclamationRepository.findById(reclamationId).orElse(null);
    }

    @Override
    public Reclamation createReclamation(Reclamation reclamation) {
        reclamation.setCreatedAt(LocalDateTime.now());
        reclamation.setUpdatedAt(LocalDateTime.now());
        reclamation.setIsDeleted(0);
        reclamation.setReclamationStatus("PENDING");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof org.keycloak.KeycloakPrincipal<?> keycloakPrincipal) {
            String createdBy = keycloakPrincipal.getName();
            reclamation.setCreatedBy(createdBy);
            reclamation.setUpdatedBy(createdBy);
        }
        return reclamationRepository.save(reclamation);
    }

    @Override
    public Reclamation updateReclamation(Long reclamationId, Reclamation reclamation) {
        reclamation.setUpdatedAt(LocalDateTime.now());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof org.keycloak.KeycloakPrincipal<?> keycloakPrincipal) {
            String updatedBy = keycloakPrincipal.getName();
            reclamation.setUpdatedBy(updatedBy);
        }
        return reclamationRepository.save(reclamation);
    }

    @Override
    public void deleteReclamation(Long reclamationId) {
        //delete
        reclamationRepository.findById(reclamationId).ifPresent(reclamation -> {
            reclamation.setIsDeleted(1);
            reclamationRepository.save(reclamation);
        });
    }

    @Override
    public List<Reclamation> getAllReclamationsByUserId(String userId) {
        return reclamationRepository.findByKeycloakUserId(userId);
    }


    @Override
    @Transactional
    public void deleteOldReclamations() {
        LocalDateTime threeDaysAgo = LocalDateTime.now().minusDays(3);
        reclamationRepository.markAsDeleted("REFUSE", threeDaysAgo);
        reclamationRepository.markAsDeleted("TRAITE", threeDaysAgo);
    }
}
