package com.timemanagemenet.timemanagementapp.service.departement;
import com.timemanagemenet.timemanagementapp.entity.Departement;
import com.timemanagemenet.timemanagementapp.repository.DepartementRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.beans.PropertyDescriptor;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

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
        Optional<Departement> existingDepartementOptional = departementRepository.findById(departementId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (existingDepartementOptional.isPresent()) {
            Departement existingDepartement = existingDepartementOptional.get();
            BeanUtils.copyProperties(updatedDepartement, existingDepartement, getNullPropertyNames(updatedDepartement));

            existingDepartement.setUpdatedAt(LocalDateTime.now());

            if(authentication != null && authentication.getPrincipal() instanceof org.keycloak.KeycloakPrincipal<?> keycloakPrincipal) {
                String updatedBy = keycloakPrincipal.getName();
                existingDepartement.setUpdatedBy(updatedBy);
            }

            return departementRepository.save(existingDepartement);
        }

        return null; // Return appropriate response or throw an exception if not found
    }

    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
        return Stream.of(wrappedSource.getPropertyDescriptors())
                .map(PropertyDescriptor::getName)
                .filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null)
                .toArray(String[]::new);
    }


    @Override
    public void deleteDepartement(Long departementId) {
        departementRepository.deleteById(departementId);
    }

}