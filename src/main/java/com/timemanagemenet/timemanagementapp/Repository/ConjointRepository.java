package com.timemanagemenet.timemanagementapp.Repository;

import com.timemanagemenet.timemanagementapp.Entity.Conjoint;
import com.timemanagemenet.timemanagementapp.Entity.ConjointId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConjointRepository extends JpaRepository<Conjoint, ConjointId> {
    Conjoint findByIdConjoint_EmployeeIdAndIdConjoint_ConjointId(Long employeeId, Integer conjointId);
}