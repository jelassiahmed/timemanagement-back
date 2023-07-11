package com.timemanagemenet.timemanagementapp.Service.Planning;

import com.timemanagemenet.timemanagementapp.Entity.Planning;

import java.util.List;

public interface PlanningService {
    Planning savePlanning(Planning planning);
    Planning updatePlanning(Planning planning);
    void deletePlanning(Long planningId);
    Planning getPlanningById(Long planningId);
    List<Planning> getAllPlannings();
}
