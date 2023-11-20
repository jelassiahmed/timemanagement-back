package com.timemanagemenet.timemanagementapp.Repository;

import com.timemanagemenet.timemanagementapp.Entity.Reclamation;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

public interface ReclamationRepository extends JpaRepository<Reclamation, Long> {
    List<Reclamation> findByKeycloakUserId(String userId);

    @Transactional
    void deleteByReclamationStatusAndUpdatedAtBefore(String status, LocalDateTime updatedAt);
}
