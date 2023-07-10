package com.timemanagemenet.timemanagementapp.Service.Absence;

import com.timemanagemenet.timemanagementapp.Entity.Absence;

import java.util.List;
import java.util.Optional;

public interface AbsenceService {
    List<Absence> getAllAbsences();
    Absence getAbsenceById(Long id);
    Absence createAbsence(Absence absence);
    Absence updateAbsence(Long id, Absence absence);
    void deleteAbsence(Long id);

    List<Absence> getAbsencesByUser(String keycloakUserId);
}
