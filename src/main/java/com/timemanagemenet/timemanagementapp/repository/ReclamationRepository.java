package com.timemanagemenet.timemanagementapp.repository;

import com.timemanagemenet.timemanagementapp.entity.Reclamation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ReclamationRepository extends JpaRepository<Reclamation, Long> {
    List<Reclamation> findByKeycloakUserId(String userId);

    @Modifying
    @Query("UPDATE Reclamation r SET r.isDeleted = 1 WHERE r.reclamationStatus = :status AND r.updatedAt < :updatedAt")
    void markAsDeleted(@Param("status") String status,@Param("updatedAt") LocalDateTime updatedAt);
}
