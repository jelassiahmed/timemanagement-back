package com.timemanagemenet.timemanagementapp.service.conjoint;

import com.timemanagemenet.timemanagementapp.entity.Conjoint;
import com.timemanagemenet.timemanagementapp.repository.ConjointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ConjointServiceImpl implements ConjointService {

    @Autowired
    private ConjointRepository conjointRepository;

    @Override
    public Conjoint saveConjoint(Conjoint conjoint) {
        conjoint.setCreatedAt(LocalDateTime.now());
        conjoint.setUpdatedAt(LocalDateTime.now());
        conjoint.setIsDeleted(0);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof org.keycloak.KeycloakPrincipal<?> keycloakPrincipal) {
            String createdBy = keycloakPrincipal.getName();
            conjoint.setCreatedBy(createdBy);
            conjoint.setUpdatedBy(createdBy);
        }
        return conjointRepository.save(conjoint);
    }

    @Override
    public Conjoint getConjointById(Long employeeId, Integer conjointId) {
        return conjointRepository.findByIdConjoint_EmployeeIdAndIdConjoint_ConjointId(employeeId, conjointId);
    }

    @Override
    public void deleteConjointById(Long employeeId, Integer conjointId) {
        Conjoint conjoint = getConjointById(employeeId, conjointId);
        if (conjoint != null) {
            conjoint.setIsDeleted(1);
            conjointRepository.save(conjoint);
        }
    }

    @Override
    public List<Conjoint> getConjointsByEmployeeId(Long employeeId) {
        return conjointRepository.findByIdConjoint_EmployeeId(employeeId);
    }
}