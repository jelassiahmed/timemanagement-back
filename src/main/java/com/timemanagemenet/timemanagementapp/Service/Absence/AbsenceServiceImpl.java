package com.timemanagemenet.timemanagementapp.Service.Absence;

import com.timemanagemenet.timemanagementapp.Entity.Absence;
import com.timemanagemenet.timemanagementapp.Repository.AbsenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Absence createAbsence(Absence absence) {
        return absenceRepository.save(absence);
    }

    @Override
    public Absence updateAbsence(Long id, Absence updatedAbsence) {
        Optional<Absence> existingAbsence = absenceRepository.findById(id);
        if (existingAbsence.isPresent()) {
            updatedAbsence.setIdAbsence(id);
            return absenceRepository.save(updatedAbsence);
        }
        return null;
    }

    @Override
    public void deleteAbsence(Long id) {
        absenceRepository.deleteById(id);
    }

    @Override
    public List<Absence> getAbsencesByUser(String keycloakUserId) {
        return absenceRepository.findByKeycloakUserId(keycloakUserId);
    }
}
