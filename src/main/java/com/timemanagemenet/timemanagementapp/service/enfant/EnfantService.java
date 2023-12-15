package com.timemanagemenet.timemanagementapp.service.enfant;

import com.timemanagemenet.timemanagementapp.entity.Enfant;
import com.timemanagemenet.timemanagementapp.entity.EnfantId;

import java.util.List;

public interface EnfantService {
    List<Enfant> getAllEnfants();
    Enfant getEnfantById(EnfantId id);
    Enfant saveEnfant(Enfant enfant);
    void deleteEnfant(EnfantId id);

    List<Enfant> getEnfantByFatherId(Long fatherId);

    List<Enfant> getEnfantByConjointId(Integer conjointId);
}
