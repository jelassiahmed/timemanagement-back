package com.timemanagemenet.timemanagementapp.Service.Departement;

import com.timemanagemenet.timemanagementapp.Entity.Departement;

import java.util.List;

public interface DepartementService {
    List<Departement> getAllDepartements();

    Departement getDepartementById(Long departementId);

    Departement createDepartement(Departement departement);

    Departement updateDepartement(Long departementId, Departement updatedDepartement);

    void deleteDepartement(Long departementId);
}
