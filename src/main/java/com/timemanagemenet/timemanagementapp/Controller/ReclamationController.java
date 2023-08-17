package com.timemanagemenet.timemanagementapp.Controller;

import com.timemanagemenet.timemanagementapp.Entity.Reclamation;
import com.timemanagemenet.timemanagementapp.Service.Reclamation.ReclamationService;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reclamation")
public class ReclamationController {
    private final ReclamationService reclamationService;

    public ReclamationController(ReclamationService reclamationService) {
        this.reclamationService = reclamationService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Reclamation>> getAllReclamations() {
        List<Reclamation> reclamations = reclamationService.getAllReclamations();
        return ResponseEntity.ok(reclamations);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Reclamation> getReclamationById(@PathVariable("id") Long id) {
        Reclamation reclamation = reclamationService.getReclamationById(id);
        return ResponseEntity.ok(reclamation);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Reclamation>> getReclamationsByUserId(
            @PathVariable("id") String id,
            @AuthenticationPrincipal KeycloakAuthenticationToken authenticationToken) {
        if (authenticationToken.getAccount().getKeycloakSecurityContext().getToken().getSubject().equals(id)) {
            List<Reclamation> reclamations = reclamationService.getAllReclamationsByUserId(id);
            return ResponseEntity.ok(reclamations);
        } else {
            return ResponseEntity.badRequest().build();
    }

    }

    @PostMapping
    public ResponseEntity<Reclamation> createReclamation(@RequestBody Reclamation reclamation) {
        Reclamation createdReclamation = reclamationService.createReclamation(reclamation);
        return ResponseEntity.ok(createdReclamation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reclamation> updateReclamation(
            @PathVariable("id") Long id,
            @RequestBody Reclamation reclamation,
    @AuthenticationPrincipal KeycloakAuthenticationToken authenticationToken) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
        if (authenticationToken.getAccount().getKeycloakSecurityContext().getToken().getSubject().equals(reclamation.getKeycloakUserId())
                || isAdmin) {
            Reclamation updatedReclamation = reclamationService.updateReclamation(id, reclamation);
            return ResponseEntity.ok(updatedReclamation);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReclamation(
            @PathVariable("id") Long id,
            @AuthenticationPrincipal KeycloakAuthenticationToken authenticationToken) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
Reclamation reclamation = reclamationService.getReclamationById(id);
        if (authenticationToken.getAccount().getKeycloakSecurityContext().getToken().getSubject().equals(reclamation.getKeycloakUserId())
                || isAdmin) {
            reclamationService.deleteReclamation(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


}
