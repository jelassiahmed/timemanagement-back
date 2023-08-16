package com.timemanagemenet.timemanagementapp.Service.Absence;

import com.timemanagemenet.timemanagementapp.Entity.Absence;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;

public interface AbsenceService {
        Absence getById(Long id);
        List<Absence> getAll();
        Absence create(Absence absence);
        Absence update(Long id, Absence updatedAbsence);
        void delete(Long id);

        List<Absence> getAbsencesByUser(String keycloakUserId);
}
