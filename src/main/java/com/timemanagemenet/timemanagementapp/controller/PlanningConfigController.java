package com.timemanagemenet.timemanagementapp.controller;

import com.timemanagemenet.timemanagementapp.entity.PlanningConfig;

import com.timemanagemenet.timemanagementapp.service.planningconfig.PlanningConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));

        if (!isAdmin) {
            return null;
        }
        return planningConfigService.getAllPlanningConfigs();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public PlanningConfig getPlanningConfigById(@PathVariable("id") Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));

        if (!isAdmin) {
            return null;
        }
        return planningConfigService.getPlanningConfigById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public PlanningConfig createPlanningConfig(@RequestBody PlanningConfig planningConfig) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));

        if (!isAdmin) {
            return null;
        }
        return planningConfigService.createPlanningConfig(planningConfig);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public PlanningConfig updatePlanningConfig(
            @PathVariable("id") Long id,
            @RequestBody PlanningConfig planningConfig
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));

        if (!isAdmin) {
            return null;
        }
        return planningConfigService.updatePlanningConfig(id, planningConfig);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deletePlanningConfig(@PathVariable("id") Long id) {
        planningConfigService.deletePlanningConfig(id);
    }
}
