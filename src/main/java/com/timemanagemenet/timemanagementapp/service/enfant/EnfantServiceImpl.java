package com.timemanagemenet.timemanagementapp.service.enfant;

import com.timemanagemenet.timemanagementapp.entity.Enfant;
import com.timemanagemenet.timemanagementapp.entity.EnfantId;
import com.timemanagemenet.timemanagementapp.repository.EnfantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EnfantServiceImpl implements EnfantService {

    @Autowired
    private EnfantRepository enfantRepository;

    @Override
    public List<Enfant> getAllEnfants() {
        return enfantRepository.findAll();
    }

    @Override
    public Enfant getEnfantById(EnfantId id) {
        return enfantRepository.findById(id).orElse(null);
    }

    @Override
    public Enfant saveEnfant(Enfant enfant) {
        enfant.setCreatedAt(LocalDateTime.now());
        enfant.setUpdatedAt(LocalDateTime.now());
        enfant.setIsDeleted(0);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.getPrincipal() instanceof org.keycloak.KeycloakPrincipal<?> keycloakPrincipal) {
            String createdBy = keycloakPrincipal.getName();
            enfant.setCreatedBy(createdBy);
            enfant.setUpdatedBy(createdBy);
        }
        enfantRepository.save(enfant);
        return enfant;
    }

    @Override
    public List<Enfant> getEnfantByFatherId(Long fatherId) {
        return enfantRepository.findById_FatherId(fatherId);
    }

    @Override
    public List<Enfant> getEnfantByConjointId(Integer conjointId) {
        return enfantRepository.findById_ConjointId(conjointId);
    }

    @Override
    public void deleteEnfant(EnfantId id) {
        enfantRepository.deleteById(id);
    }
}
