package com.timemanagemenet.timemanagementapp.Controller;

import com.timemanagemenet.timemanagementapp.Entity.Departement;
import com.timemanagemenet.timemanagementapp.Service.Departement.DepartementService;
import com.timemanagemenet.timemanagementapp.Service.KeycloakService;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/departments")
public class DepartementController {
   /* private final DepartementService departementService;
    private final KeycloakService keycloakService;

    @Autowired
    public DepartementController(DepartementService departementService, KeycloakService keycloakService) {
        this.departementService = departementService;
        this.keycloakService = keycloakService;
    }

    @PostMapping
    @PreAuthorize("hasRole('admin')")
    public Departement createDepartement(@RequestBody Departement departement) {
        return departementService.createDepartement(departement);
    }

    @GetMapping("/{departementId}")
    public ResponseEntity<Departement> getDepartementById(@PathVariable Long departementId, KeycloakAuthenticationToken authentication) {
        Optional<Departement> departement = departementService.getDepartementById(departementId);

        if (departement.isPresent()) {
            if (isAdmin(authentication) || isDepartementOwner(departement.get(), authentication)) {
                return ResponseEntity.ok(departement.get());
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public List<Departement> getAllDepartements(KeycloakAuthenticationToken authentication) {
        if (isAdmin(authentication)) {
            return departementService.getAllDepartements();
        } else {
            String userId = getUserId(authentication);
            List<Departement> userDepartments = departementService.getDepartementsByUserId(userId);
            return userDepartments;
        }
    }

    @PutMapping("/{departementId}")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<Departement> updateDepartement(@PathVariable Long departementId, @RequestBody Departement departement) {
        Optional<Departement> existingDepartement = departementService.getDepartementById(departementId);

        if (existingDepartement.isPresent()) {
            departement.setDepartementId(departementId);
            return ResponseEntity.ok(departementService.updateDepartement(departement));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{departementId}")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<Void> deleteDepartement(@PathVariable Long departementId) {
        Optional<Departement> departement = departementService.getDepartementById(departementId);

        if (departement.isPresent()) {
            departementService.deleteDepartement(departementId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private boolean isAdmin(KeycloakAuthenticationToken authentication) {
        return authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_admin"));
    }

    private boolean isDepartementOwner(Departement departement, KeycloakAuthenticationToken authentication) {
        String userId = getUserId(authentication);
        return departement.getKeycloakUserId().contains(userId);
    }

    private String getUserId(KeycloakAuthenticationToken authentication) {
        String username = authentication.getName();
        return keycloakService.getUserIdByUsername(username);
    }
*/}
