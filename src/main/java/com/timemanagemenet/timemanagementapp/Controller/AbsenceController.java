package com.timemanagemenet.timemanagementapp.Controller;

import com.timemanagemenet.timemanagementapp.Entity.Absence;
import com.timemanagemenet.timemanagementapp.Entity.WebSocketMessage;
import com.timemanagemenet.timemanagementapp.Service.Absence.AbsenceService;
import com.timemanagemenet.timemanagementapp.config.WebSocket.Greeting;
import org.keycloak.KeycloakPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/absences")
public class AbsenceController {

    private final AbsenceService absenceService;
    final WebSocketController webSocketController;
    @Autowired
    public AbsenceController(AbsenceService absenceService,WebSocketController webSocketController) {
        this.absenceService = absenceService;
        this.webSocketController = webSocketController;
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

        Absence createdAbsence = absenceService.create(absence);
        webSocketController.sendMessage(new WebSocketMessage("add absence"+ createdAbsence.getIdAbsence()));

        return createdAbsence;
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Absence updateAbsence(@PathVariable Long id, @RequestBody Absence updatedAbsence) {
        Absence absence = absenceService.update(id, updatedAbsence);
        webSocketController.sendMessage(new WebSocketMessage("update absence"+ absence.getIdAbsence()));
        return absence;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteAbsence(@PathVariable Long id) {

        absenceService.delete(id);
        webSocketController.sendMessage(new WebSocketMessage("delete absence"));
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
