package com.timemanagemenet.timemanagementapp.service.diplome;

import com.timemanagemenet.timemanagementapp.entity.Diplome;

import java.util.List;

public interface DiplomeService {
    Diplome getDiplomeById(Long id);
    Diplome saveDiplome(Diplome diplome);
    Diplome updateDiplome(Long id, Diplome diplome);
    void deleteDiplome(Long id);

    public List<Diplome> getDiplomeByUserId(Long id);
}
