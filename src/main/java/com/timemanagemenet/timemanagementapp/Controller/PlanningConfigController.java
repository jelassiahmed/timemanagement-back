package com.timemanagemenet.timemanagementapp.Controller;

import com.timemanagemenet.timemanagementapp.Entity.PlanningConfig;

import com.timemanagemenet.timemanagementapp.Service.PlanningConfig.PlanningConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/planning-config")
public class PlanningConfigController {
    private final PlanningConfigService planningConfigService;

    @Autowired
    public PlanningConfigController(PlanningConfigService planningConfigService) {
        this.planningConfigService = planningConfigService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<PlanningConfig> getAllPlanningConfigs() {
        return planningConfigService.getAllPlanningConfigs();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public PlanningConfig getPlanningConfigById(@PathVariable("id") Long id) {
        return planningConfigService.getPlanningConfigById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public PlanningConfig createPlanningConfig(@RequestBody PlanningConfig planningConfig) {
        return planningConfigService.createPlanningConfig(planningConfig);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public PlanningConfig updatePlanningConfig(
            @PathVariable("id") Long id,
            @RequestBody PlanningConfig planningConfig
    ) {
        return planningConfigService.updatePlanningConfig(id, planningConfig);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deletePlanningConfig(@PathVariable("id") Long id) {
        planningConfigService.deletePlanningConfig(id);
    }
}
