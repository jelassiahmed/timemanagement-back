package com.timemanagemenet.timemanagementapp.Service.Conjoint;

import com.timemanagemenet.timemanagementapp.Entity.Conjoint;

public interface ConjointService {
    Conjoint saveConjoint(Conjoint conjoint);
    Conjoint getConjointById(Long employeeId, Integer conjointId);
    void deleteConjointById(Long employeeId, Integer conjointId);
}
