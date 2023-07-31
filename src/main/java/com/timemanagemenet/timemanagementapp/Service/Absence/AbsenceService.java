package com.timemanagemenet.timemanagementapp.Service.Absence;

import com.timemanagemenet.timemanagementapp.Entity.Absence;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;
import java.util.Optional;

public interface AbsenceService {
    List<Absence> getAllAbsences();
    Absence getAbsenceById(Long id);
    Absence addReclamation(Long absenceId, String reclamationDescription);
    Absence createAbsence(Absence absence, @AuthenticationPrincipal Authentication authentication);
    Absence updateAbsence(Long id, Absence updatedAbsence, @AuthenticationPrincipal Authentication authentication);
    void deleteAbsence(Long id);

    List<Absence> getAbsencesByUser(String keycloakUserId);
}
