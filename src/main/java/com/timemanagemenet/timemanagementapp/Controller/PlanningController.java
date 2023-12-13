package com.timemanagemenet.timemanagementapp.Controller;

import com.timemanagemenet.timemanagementapp.Entity.Planning;
import com.timemanagemenet.timemanagementapp.Entity.WebSocketMessage;
import com.timemanagemenet.timemanagementapp.Service.Planning.PlanningService;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/plannings")
public class PlanningController {
    private final PlanningService planningService;
    private final WebSocketController webSocketController;

    public PlanningController(PlanningService planningService, WebSocketController webSocketController) {
        this.planningService = planningService;
        this.webSocketController = webSocketController;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Planning> createPlanning(@RequestBody Planning planning) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));

        if (!isAdmin) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Planning createdPlanning = planningService.savePlanning(planning);
        webSocketController.sendMessage(new WebSocketMessage(" new planning created", createdPlanning.getPlanningId()));
        return ResponseEntity.ok(createdPlanning);
    }

    @PutMapping("/{planningId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Planning> updatePlanning(@PathVariable Long planningId, @RequestBody Planning planning) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));

        if (!isAdmin) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Planning existingPlanning = planningService.getPlanningById(planningId);
        if (existingPlanning == null) {
            return ResponseEntity.notFound().build();
        }
        Planning updatedPlanning = planningService.updatePlanning(planning);
        webSocketController.sendMessage(new WebSocketMessage(" planning updated", updatedPlanning.getPlanningId()));
        return ResponseEntity.ok(updatedPlanning);
    }

    @DeleteMapping("/{planningId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deletePlanning(@PathVariable Long planningId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));

        if (!isAdmin) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Planning existingPlanning = planningService.getPlanningById(planningId);
        if (existingPlanning == null) {
            return ResponseEntity.notFound().build();
        }
        planningService.deletePlanning(planningId);
        webSocketController.sendMessage(new WebSocketMessage(" planning deleted", planningId));
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{planningId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Planning> getPlanningById(@PathVariable Long planningId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));

        if (!isAdmin) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Planning planning = planningService.getPlanningById(planningId);
        if (planning == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(planning);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Planning>> getAllPlannings(Authentication authentication) {
        // Retrieve the authenticated user's details
        KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) authentication;
        KeycloakPrincipal principal = (KeycloakPrincipal) token.getPrincipal();
        String userId = principal.getName();

        List<Planning> userPlannings = planningService.getAllPlannings();
        return ResponseEntity.ok(userPlannings);
    }
}
