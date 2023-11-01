package com.timemanagemenet.timemanagementapp.Service.Conjoint;

import com.timemanagemenet.timemanagementapp.Entity.Conjoint;

import java.util.List;

public interface ConjointService {
    Conjoint saveConjoint(Conjoint conjoint);
    Conjoint getConjointById(Long employeeId, Integer conjointId);
    void deleteConjointById(Long employeeId, Integer conjointId);
    public List<Conjoint> getConjointsByEmployeeId(Long employeeId);
}
