package com.timemanagemenet.timemanagementapp.Service.Enfant;

import com.timemanagemenet.timemanagementapp.Entity.Enfant;
import com.timemanagemenet.timemanagementapp.Entity.EnfantId;

import java.util.List;

public interface EnfantService {
    List<Enfant> getAllEnfants();
    Enfant getEnfantById(EnfantId id);
    Enfant saveEnfant(Enfant enfant);
    void deleteEnfant(EnfantId id);

    List<Enfant> getEnfantByFatherId(Long fatherId);

    List<Enfant> getEnfantByConjointId(Integer conjointId);
}
