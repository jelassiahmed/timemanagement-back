package com.timemanagemenet.timemanagementapp.service.conjoint;

import com.timemanagemenet.timemanagementapp.entity.Conjoint;

import java.util.List;

public interface ConjointService {
    Conjoint saveConjoint(Conjoint conjoint);
    Conjoint getConjointById(Long employeeId, Integer conjointId);
    void deleteConjointById(Long employeeId, Integer conjointId);
    public List<Conjoint> getConjointsByEmployeeId(Long employeeId);
}
