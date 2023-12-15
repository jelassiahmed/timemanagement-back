package com.timemanagemenet.timemanagementapp.repository;

import com.timemanagemenet.timemanagementapp.entity.Absence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AbsenceRepository extends JpaRepository<Absence, Long> {
    List<Absence> findByKeycloakUserId(String keycloakUserId);
}