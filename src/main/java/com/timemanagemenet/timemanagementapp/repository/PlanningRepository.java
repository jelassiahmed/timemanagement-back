package com.timemanagemenet.timemanagementapp.repository;

import com.timemanagemenet.timemanagementapp.entity.Planning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanningRepository extends JpaRepository<Planning, Long> {
}