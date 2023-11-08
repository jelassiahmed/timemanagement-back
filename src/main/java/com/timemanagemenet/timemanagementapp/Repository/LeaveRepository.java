package com.timemanagemenet.timemanagementapp.Repository;

import com.timemanagemenet.timemanagementapp.Entity.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Long> {
    List<Leave> findByKeycloakUserId(String userId);
}
