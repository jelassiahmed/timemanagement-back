package com.timemanagemenet.timemanagementapp.Repository;

import com.timemanagemenet.timemanagementapp.Entity.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartementRepository extends JpaRepository<Departement, Long> {

    List<Departement> findByKeycloakUserId(String userId);
}
