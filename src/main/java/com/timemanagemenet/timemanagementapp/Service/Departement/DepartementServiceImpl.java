package com.timemanagemenet.timemanagementapp.Service.Departement;
import com.timemanagemenet.timemanagementapp.Entity.Departement;
import com.timemanagemenet.timemanagementapp.Repository.DepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DepartementServiceImpl implements DepartementService {
    private final DepartementRepository departementRepository;

    @Autowired
    public DepartementServiceImpl(DepartementRepository departementRepository) {
        this.departementRepository = departementRepository;
    }

    @Override
    public List<Departement> getAllDepartements() {
        return departementRepository.findAll();
    }

    @Override
    public Departement getDepartementById(Long departementId) {
        return departementRepository.findById(departementId).orElse(null);
    }

    @Override
    public Departement createDepartement(Departement departement) {

        departement.setCreatedAt(LocalDateTime.now());
        departement.setUpdatedAt(LocalDateTime.now());
        departement.setIsDeleted(0);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof org.keycloak.KeycloakPrincipal<?> keycloakPrincipal) {
            String createdBy = keycloakPrincipal.getName();
            departement.setCreatedBy(createdBy);
            departement.setUpdatedBy(createdBy);
        }
        return departementRepository.save(departement);
    }

    @Override
    public Departement updateDepartement(Long departementId, Departement updatedDepartement) {
        Optional<Departement> existingDepartement = departementRepository.findById(departementId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (existingDepartement.isPresent()) {
            updatedDepartement.setUpdatedAt(LocalDateTime.now());
            if(authentication != null && authentication.getPrincipal() instanceof org.keycloak.KeycloakPrincipal<?> keycloakPrincipal) {
                String updatedBy = keycloakPrincipal.getName();
                updatedDepartement.setUpdatedBy(updatedBy);
            }
            updatedDepartement.setDepartementId(departementId);
            return departementRepository.save(updatedDepartement);
        }
        return null; // Return appropriate response or throw exception if not found
    }

    @Override
    public void deleteDepartement(Long departementId) {
        departementRepository.deleteById(departementId);
    }

}