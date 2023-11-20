package com.timemanagemenet.timemanagementapp.Service.Reclamation;

import com.timemanagemenet.timemanagementapp.Entity.Reclamation;

import java.util.List;

public interface ReclamationService {
    List<Reclamation> getAllReclamations();

    Reclamation getReclamationById(Long reclamationId);

    Reclamation createReclamation(Reclamation reclamation);

    Reclamation updateReclamation(Long reclamationId, Reclamation reclamation);

    void deleteReclamation(Long reclamationId);

    List<Reclamation> getAllReclamationsByUserId(String userId);
    public void deleteOldReclamations();
}
