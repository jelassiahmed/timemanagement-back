package com.timemanagemenet.timemanagementapp.repository;

import com.timemanagemenet.timemanagementapp.entity.Enfant;
import com.timemanagemenet.timemanagementapp.entity.EnfantId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnfantRepository extends JpaRepository<Enfant, EnfantId> {
    List<Enfant> findById_FatherId(Long fatherId);
    List<Enfant> findById_ConjointId(Integer conjointId);

}