package com.timemanagemenet.timemanagementapp.Service.Diplome;

import com.timemanagemenet.timemanagementapp.Entity.Diplome;
import com.timemanagemenet.timemanagementapp.Repository.DiplomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class DiplomeServiceImpl implements DiplomeService {

    @Autowired
    private DiplomeRepository diplomeRepository;

    @Override
    public Diplome getDiplomeById(Long id) {
        return diplomeRepository.findById(id).orElse(null);
    }

    @Override
    public void saveDiplome(Diplome diplome) {
        diplome.setCreatedAt(LocalDateTime.now());
        diplome.setUpdatedAt(LocalDateTime.now());
        diplome.setIsDeleted(0);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof org.keycloak.KeycloakPrincipal<?> keycloakPrincipal) {
            String createdBy = keycloakPrincipal.getName();
            diplome.setCreatedBy(createdBy);
            diplome.setUpdatedBy(createdBy);
        }
        diplomeRepository.save(diplome);
    }

    @Override
    public void updateDiplome(Long id, Diplome updatedDiplome) {
        Optional<Diplome> existingDiplome = diplomeRepository.findById(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(existingDiplome.isPresent())
        {
            Diplome diplome = existingDiplome.get();
            diplome.setUpdatedAt(LocalDateTime.now());
            if (authentication != null && authentication.getPrincipal() instanceof org.keycloak.KeycloakPrincipal<?> keycloakPrincipal) {
                String updatedBy = keycloakPrincipal.getName();
                diplome.setUpdatedBy(updatedBy);
            }
            updatedDiplome.setIdDiplome(id);
            diplomeRepository.save(updatedDiplome);
        }
    }

    @Override
    public void deleteDiplome(Long id) {
        diplomeRepository.deleteById(id);
    }
}