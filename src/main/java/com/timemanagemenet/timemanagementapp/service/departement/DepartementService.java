package com.timemanagemenet.timemanagementapp.service.departement;

import com.timemanagemenet.timemanagementapp.entity.Departement;

import java.util.List;

public interface DepartementService {
    List<Departement> getAllDepartements();

    Departement getDepartementById(Long departementId);

    Departement createDepartement(Departement departement);

    Departement updateDepartement(Long departementId, Departement updatedDepartement);

    void deleteDepartement(Long departementId);

}
