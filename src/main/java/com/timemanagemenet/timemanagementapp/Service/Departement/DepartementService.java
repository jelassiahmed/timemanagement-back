package com.timemanagemenet.timemanagementapp.Service.Departement;

import com.timemanagemenet.timemanagementapp.Entity.Departement;

import java.util.List;
import java.util.Optional;

public interface DepartementService {
    Departement createDepartement(Departement departement);

    Optional<Departement> getDepartementById(Long departementId);

    List<Departement> getAllDepartements();

    Departement updateDepartement(Departement departement);

    void deleteDepartement(Long departementId);


    List<Departement> getDepartementsByUserId(String userId);
}
