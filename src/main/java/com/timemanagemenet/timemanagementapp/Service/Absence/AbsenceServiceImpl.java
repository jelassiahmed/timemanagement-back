package com.timemanagemenet.timemanagementapp.Service.Absence;

import com.timemanagemenet.timemanagementapp.Entity.Absence;
import com.timemanagemenet.timemanagementapp.Repository.AbsenceRepository;
import org.keycloak.KeycloakPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AbsenceServiceImpl implements AbsenceService {
    private final AbsenceRepository absenceRepository;

    @Autowired
    public AbsenceServiceImpl(AbsenceRepository absenceRepository) {
        this.absenceRepository = absenceRepository;
    }

    @Override
    public Absence getById(Long id) {
        Optional<Absence> absence = absenceRepository.findById(id);
        return absence.orElse(null);
    }

    @Override
    public List<Absence> getAll() {
        return absenceRepository.findAll();
    }

    @Override
    public Absence create(Absence absence) {

        absence.setCreatedAt(LocalDateTime.now());
        absence.setUpdatedAt(LocalDateTime.now());
        absence.setIsDeleted(0);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof KeycloakPrincipal) {
            KeycloakPrincipal<?> keycloakPrincipal = (KeycloakPrincipal<?>) authentication.getPrincipal();
            String createdBy = keycloakPrincipal.getName();
            absence.setCreatedBy(createdBy);
            absence.setUpdatedBy(createdBy);
        }
        return absenceRepository.save(absence);
    }

    @Override
    public Absence update(Long id, Absence updatedAbsence) {
        Optional<Absence> existingAbsence = absenceRepository.findById(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (existingAbsence.isPresent()) {
            Absence absence = existingAbsence.get();
            absence.setUpdatedAt(LocalDateTime.now());
            if (authentication != null && authentication.getPrincipal() instanceof KeycloakPrincipal) {
                KeycloakPrincipal<?> keycloakPrincipal = (KeycloakPrincipal<?>) authentication.getPrincipal();
                String updatedBy = keycloakPrincipal.getName();
                absence.setUpdatedBy(updatedBy);
            }
            updatedAbsence.setIdAbsence(id);
            return absenceRepository.save(updatedAbsence);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        absenceRepository.deleteById(id);
    }

    @Override
    public List<Absence> getAbsencesByUser(String keycloakUserId) {
        return absenceRepository.findByKeycloakUserId(keycloakUserId);
    }
}
