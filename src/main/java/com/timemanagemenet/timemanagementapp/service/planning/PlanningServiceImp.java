package com.timemanagemenet.timemanagementapp.service.planning;

import com.timemanagemenet.timemanagementapp.entity.Planning;
import com.timemanagemenet.timemanagementapp.repository.PlanningRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PlanningServiceImp implements PlanningService {
    private final PlanningRepository planningRepository;

    public PlanningServiceImp(PlanningRepository planningRepository) {
        this.planningRepository = planningRepository;
    }

    @Override
    public Planning savePlanning(Planning planning) {
        planning.setCreatedAt(LocalDateTime.now());
        planning.setUpdatedAt(LocalDateTime.now());
        planning.setIsDeleted(0);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof org.keycloak.KeycloakPrincipal<?> keycloakPrincipal) {
            String createdBy = keycloakPrincipal.getName();
            planning.setCreatedBy(createdBy);
            planning.setUpdatedBy(createdBy);
        }
        return planningRepository.save(planning);
    }

    @Override
    public Planning updatePlanning(Planning planning) {
        planning.setUpdatedAt(LocalDateTime.now());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof org.keycloak.KeycloakPrincipal<?> keycloakPrincipal) {
            String updatedBy = keycloakPrincipal.getName();
            planning.setUpdatedBy(updatedBy);
        }

        return planningRepository.save(planning);
    }

    @Override
    public void deletePlanning(Long planningId) {
        planningRepository.deleteById(planningId);
    }

    @Override
    public Planning getPlanningById(Long planningId) {
        return planningRepository.findById(planningId).orElse(null);
    }

    @Override
    public List<Planning> getAllPlannings() {
        return planningRepository.findAll();
    }

}