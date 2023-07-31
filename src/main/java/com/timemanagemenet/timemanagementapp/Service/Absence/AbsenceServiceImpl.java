package com.timemanagemenet.timemanagementapp.Service.Absence;

import com.timemanagemenet.timemanagementapp.Entity.Absence;
import com.timemanagemenet.timemanagementapp.Repository.AbsenceRepository;
import org.keycloak.KeycloakPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public List<Absence> getAllAbsences() {
        return absenceRepository.findAll();
    }

    @Override
    public Absence getAbsenceById(Long id) {
        Optional<Absence> absence = absenceRepository.findById(id);
        return absence.orElse(null);
    }

    @Override
    public Absence createAbsence(Absence absence, @AuthenticationPrincipal Authentication authentication) {
        absence.setReclamation(false);
        absence.setReclamationDescription(null);
        absence.setCreatedAt(LocalDateTime.now());

        if (authentication != null && authentication.getPrincipal() instanceof KeycloakPrincipal) {
            KeycloakPrincipal<?> keycloakPrincipal = (KeycloakPrincipal<?>) authentication.getPrincipal();
            String createdBy = keycloakPrincipal.getName();
            absence.setCreatedBy(createdBy);
        }

        return absenceRepository.save(absence);
    }

    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    @Override
    public Absence addReclamation(Long absenceId, String reclamationDescription) {
        Optional<Absence> existingAbsence = absenceRepository.findById(absenceId);
        if (existingAbsence.isPresent()) {
            Absence absence = existingAbsence.get();
            absence.setReclamation(true);
            absence.setReclamationDescription(reclamationDescription);
            return absenceRepository.save(absence);
        }
        return null;
    }


    @Override
    public Absence updateAbsence(Long id, Absence updatedAbsence, @AuthenticationPrincipal Authentication authentication) {
        Optional<Absence> existingAbsence = absenceRepository.findById(id);
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
    public void deleteAbsence(Long id) {
        Optional<Absence> existingAbsence = absenceRepository.findById(id);
        if (existingAbsence.isPresent()) {
            Absence absence = existingAbsence.get();
            absence.setIsDeleted(1); // Set the isDeleted flag to 1 (true) for soft delete
            absence.setUpdatedAt(LocalDateTime.now());
            absenceRepository.save(absence);
        }
    }
    @Override
    public List<Absence> getAbsencesByUser(String keycloakUserId) {
        return absenceRepository.findByKeycloakUserId(keycloakUserId);
    }
}
