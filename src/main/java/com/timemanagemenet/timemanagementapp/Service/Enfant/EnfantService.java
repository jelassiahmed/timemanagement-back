package com.timemanagemenet.timemanagementapp.Service.Enfant;

import com.timemanagemenet.timemanagementapp.Entity.Enfant;
import com.timemanagemenet.timemanagementapp.Entity.EnfantId;

import java.util.List;

public interface EnfantService {
    List<Enfant> getAllEnfants();
    Enfant getEnfantById(EnfantId id);
    void saveEnfant(Enfant enfant);
    void deleteEnfant(EnfantId id);
}
