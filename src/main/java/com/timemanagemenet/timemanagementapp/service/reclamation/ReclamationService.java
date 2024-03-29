package com.timemanagemenet.timemanagementapp.service.reclamation;

import com.timemanagemenet.timemanagementapp.entity.Reclamation;

import javax.transaction.Transactional;
import java.util.List;

public interface ReclamationService {
    List<Reclamation> getAllReclamations();

    Reclamation getReclamationById(Long reclamationId);

    Reclamation createReclamation(Reclamation reclamation);

    Reclamation updateReclamation(Long reclamationId, Reclamation reclamation);

    void deleteReclamation(Long reclamationId);

    List<Reclamation> getAllReclamationsByUserId(String userId);

    @Transactional
     void deleteOldReclamations();
}
