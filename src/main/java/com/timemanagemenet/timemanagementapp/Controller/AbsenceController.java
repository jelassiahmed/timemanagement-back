package com.timemanagemenet.timemanagementapp.Controller;

import com.timemanagemenet.timemanagementapp.Entity.Absence;
import com.timemanagemenet.timemanagementapp.Service.Absence.AbsenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/absences")
public class AbsenceController {private final AbsenceService absenceService;

    @Autowired
    public AbsenceController(AbsenceService absenceService) {
        this.absenceService = absenceService;
    }

    @GetMapping
    public ResponseEntity<List<Absence>> getAllAbsencesUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String keycloakUserId = authentication.getName();

        List<Absence> absences = absenceService.getAbsencesByUser(keycloakUserId);
        return ResponseEntity.ok(absences);
    }
    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')") // Restricted to ROLE_ADMIN
    public ResponseEntity<List<Absence>> getAllAbsences() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));

        if (!isAdmin) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        List<Absence> absences = absenceService.getAllAbsences();
        return ResponseEntity.ok(absences);}

    @GetMapping("/{id}")
    public ResponseEntity<Absence> getAbsenceById(@PathVariable("id") Long id) {
        Absence absence = absenceService.getAbsenceById(id);
        if (absence == null) {
            return ResponseEntity.notFound().build();
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String keycloakUserId = authentication.getName();

        // Ensure the authenticated user can only see their own absence
        if (!absence.getKeycloakUserId().equals(keycloakUserId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).header("X-User-ID", keycloakUserId).build();
        }

        return ResponseEntity.ok(absence);
    }
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Absence> createAbsence(@RequestBody Absence absence) {
        Absence createdAbsence = absenceService.createAbsence(absence);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAbsence);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Absence> updateAbsence(@PathVariable("id") Long id, @RequestBody Absence absence) {
        Absence existingAbsence = absenceService.getAbsenceById(id);
        if (existingAbsence == null) {
            return ResponseEntity.notFound().build();
        }

        Absence updatedAbsence = absenceService.updateAbsence(id, absence);
        return ResponseEntity.ok(updatedAbsence);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteAbsence(@PathVariable("id") Long id) {
        Absence absence = absenceService.getAbsenceById(id);
        if (absence == null) {
            return ResponseEntity.notFound().build();
        }

        absenceService.deleteAbsence(id);
        return ResponseEntity.noContent().build();
    }
}
