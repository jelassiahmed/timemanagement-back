package com.timemanagemenet.timemanagementapp.Service.Departement;
import com.timemanagemenet.timemanagementapp.Entity.Departement;
import com.timemanagemenet.timemanagementapp.Repository.DepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartementServiceImpl implements DepartementService {
    private final DepartementRepository departementRepository;

    @Autowired
    public DepartementServiceImpl(DepartementRepository departementRepository) {
        this.departementRepository = departementRepository;
    }

    @Override
    public Departement createDepartement(Departement departement) {
        return departementRepository.save(departement);
    }

    @Override
    public Optional<Departement> getDepartementById(Long departementId) {
        return departementRepository.findById(departementId);
    }

    @Override
    public List<Departement> getAllDepartements() {
        return departementRepository.findAll();
    }

    @Override
    public Departement updateDepartement(Departement departement) {
        return departementRepository.save(departement);
    }

    @Override
    public void deleteDepartement(Long departementId) {
        departementRepository.deleteById(departementId);
    }

    @Override
    public List<Departement> getDepartementsByUserId(String userId) {
        return departementRepository.findByKeycloakUserId(userId);
    }
}
