package com.timemanagemenet.timemanagementapp.Service.Leave;

import com.timemanagemenet.timemanagementapp.Entity.Leave;
import com.timemanagemenet.timemanagementapp.Repository.LeaveRepository;
import org.keycloak.KeycloakPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    private LeaveRepository leaveRepository;

    @Override
    public List<Leave> getAllLeaves() {
        return leaveRepository.findAll();
    }

    @Override
    public Optional<Leave> getLeaveById(Long id) {
        return leaveRepository.findById(id);
    }

    @Override
    public Leave createLeave(Leave leave) {
        leave.setCreatedAt(LocalDateTime.now());
        leave.setUpdatedAt(LocalDateTime.now());
        leave.setIsDeleted(0);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof KeycloakPrincipal<?> keycloakPrincipal) {
            String createdBy = keycloakPrincipal.getName();
            leave.setCreatedBy(createdBy);
            leave.setUpdatedBy(createdBy);
        }
        return leaveRepository.save(leave);
    }

    @Override
    public Leave updateLeave(Long id, Leave leave) {
        Optional<Leave> existingLeave = leaveRepository.findById(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (existingLeave.isPresent()) {
            leave.setIdLeave(id);
            leave.setUpdatedAt(LocalDateTime.now());
            if (authentication != null && authentication.getPrincipal() instanceof KeycloakPrincipal<?> keycloakPrincipal) {
                String updatedBy = keycloakPrincipal.getName();
                leave.setUpdatedBy(updatedBy);
            }
            return leaveRepository.save(leave);
        } else {
            throw new IllegalArgumentException("Leave with id " + id + " not found");
        }
    }

    @Override
    public void deleteLeave(Long id) {
        leaveRepository.deleteById(id);
    }
}






