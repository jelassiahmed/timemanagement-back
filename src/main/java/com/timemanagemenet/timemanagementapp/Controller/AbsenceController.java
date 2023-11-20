package com.timemanagemenet.timemanagementapp.Controller;

import com.timemanagemenet.timemanagementapp.Entity.Absence;
import com.timemanagemenet.timemanagementapp.Service.Absence.AbsenceService;
import org.keycloak.KeycloakPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/absences")
public class AbsenceController {private final AbsenceService absenceService;

    @Autowired
    public AbsenceController(AbsenceService absenceService) {
        this.absenceService = absenceService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Absence getAbsenceById(@PathVariable Long id) {
        return absenceService.getById(id);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Absence> getAllAbsences() {
        return absenceService.getAll();
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Absence createAbsence(@RequestBody Absence absence) {
        return absenceService.create(absence);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Absence updateAbsence(@PathVariable Long id, @RequestBody Absence updatedAbsence) {
        return absenceService.update(id, updatedAbsence);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteAbsence(@PathVariable Long id) {

        absenceService.delete(id);
    }

    @GetMapping("/user")
    public List<Absence> getUserAbsences(@AuthenticationPrincipal KeycloakPrincipal<?> principal) {
        Set<String> roles = principal.getKeycloakSecurityContext().getToken().getRealmAccess().getRoles();

        if (roles.contains("ROLE_ADMIN")) {
            return absenceService.getAll();
        } else {
            String keycloakUserId = principal.getName();
            return absenceService.getAbsencesByUser(keycloakUserId);
        }
    }
}
