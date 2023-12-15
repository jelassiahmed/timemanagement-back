package com.timemanagemenet.timemanagementapp.service.absence;

import com.timemanagemenet.timemanagementapp.entity.Absence;

import java.util.List;

public interface AbsenceService {
        Absence getById(Long id);
        List<Absence> getAll();
        Absence create(Absence absence);
        Absence update(Long id, Absence updatedAbsence);
        void delete(Long id);

        List<Absence> getAbsencesByUser(String keycloakUserId);
}
