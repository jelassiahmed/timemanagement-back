package com.timemanagemenet.timemanagementapp.Repository;

import com.timemanagemenet.timemanagementapp.Entity.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Long> {
    List<Leave> findByKeycloakUserId(String userId);
    @Modifying
    @Query("UPDATE Leave l SET l.isDeleted = 1 WHERE l.status = :status AND l.updatedAt < :updatedAt")
    void markAsDeleted(String status, LocalDateTime updatedAt);
}
