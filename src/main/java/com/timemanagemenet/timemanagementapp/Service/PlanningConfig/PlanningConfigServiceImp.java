package com.timemanagemenet.timemanagementapp.Service.PlanningConfig;

import com.timemanagemenet.timemanagementapp.Entity.PlanningConfig;
import com.timemanagemenet.timemanagementapp.Repository.PlanningConfigRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanningConfigServiceImp implements PlanningConfigService {
    private final PlanningConfigRepository planningConfigRepository;

    @Autowired
    public PlanningConfigServiceImp(PlanningConfigRepository planningConfigRepository) {
        this.planningConfigRepository = planningConfigRepository;
    }

    @Override
    public List<PlanningConfig> getAllPlanningConfigs() {
        return planningConfigRepository.findAll();
    }

    @Override
    public PlanningConfig getPlanningConfigById(Long id) {
        Optional<PlanningConfig> planningConfigOptional = planningConfigRepository.findById(id);
        return planningConfigOptional.orElse(null);
    }

    @Override
    public PlanningConfig createPlanningConfig(PlanningConfig planningConfig) {
        return planningConfigRepository.save(planningConfig);
    }

    @Override
    public PlanningConfig updatePlanningConfig(Long id, PlanningConfig planningConfig) {
        PlanningConfig existingPlanningConfig = getPlanningConfigById(id);
        if (existingPlanningConfig == null) {
            return null;
        }
        planningConfig.setPlanningConfigId(existingPlanningConfig.getPlanningConfigId());
        return planningConfigRepository.save(planningConfig);
    }

    @Override
    public void deletePlanningConfig(Long id) {
        planningConfigRepository.deleteById(id);
    }
}