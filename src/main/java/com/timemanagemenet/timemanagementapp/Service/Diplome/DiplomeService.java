package com.timemanagemenet.timemanagementapp.Service.Diplome;

import com.timemanagemenet.timemanagementapp.Entity.Diplome;

public interface DiplomeService {
    Diplome getDiplomeById(Long id);
    void saveDiplome(Diplome diplome);
    void updateDiplome(Long id,Diplome diplome);
    void deleteDiplome(Long id);
}
