package com.timemanagemenet.timemanagementapp.service.planningconfig;
import com.timemanagemenet.timemanagementapp.entity.PlanningConfig;

import java.util.List;

public interface PlanningConfigService {
    List<PlanningConfig> getAllPlanningConfigs();

    PlanningConfig getPlanningConfigById(Long id);

    PlanningConfig createPlanningConfig(PlanningConfig planningConfig);

    PlanningConfig updatePlanningConfig(Long id, PlanningConfig planningConfig);

    void deletePlanningConfig(Long id);
}