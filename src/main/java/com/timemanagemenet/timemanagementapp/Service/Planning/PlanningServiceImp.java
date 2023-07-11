package com.timemanagemenet.timemanagementapp.Service.Planning;

import com.timemanagemenet.timemanagementapp.Entity.Planning;
import com.timemanagemenet.timemanagementapp.Repository.PlanningRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanningServiceImp implements PlanningService {
    private final PlanningRepository planningRepository;

    public PlanningServiceImp(PlanningRepository planningRepository) {
        this.planningRepository = planningRepository;
    }

    @Override
    public Planning savePlanning(Planning planning) {
        return planningRepository.save(planning);
    }

    @Override
    public Planning updatePlanning(Planning planning) {
        return planningRepository.save(planning);
    }

    @Override
    public void deletePlanning(Long planningId) {
        planningRepository.deleteById(planningId);
    }

    @Override
    public Planning getPlanningById(Long planningId) {
        return planningRepository.findById(planningId).orElse(null);
    }

    @Override
    public List<Planning> getAllPlannings() {
        return planningRepository.findAll();
    }

}