package com.timemanagemenet.timemanagementapp.Controller;

import com.timemanagemenet.timemanagementapp.Entity.Reclamation;
import com.timemanagemenet.timemanagementapp.Entity.WebSocketMessage;
import com.timemanagemenet.timemanagementapp.Service.Reclamation.ReclamationService;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reclamation")
public class ReclamationController {
    private final ReclamationService reclamationService;
    private final WebSocketController webSocketController;

    public ReclamationController(ReclamationService reclamationService, WebSocketController webSocketController) {
        this.reclamationService = reclamationService;
        this.webSocketController = webSocketController;
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

            List<Reclamation> reclamations = reclamationService.getAllReclamationsByUserId(id);
            return ResponseEntity.ok(reclamations);



    }

    @PostMapping
    public ResponseEntity<Reclamation> createReclamation(@RequestBody Reclamation reclamation) {
        Reclamation createdReclamation = reclamationService.createReclamation(reclamation);
        webSocketController.sendMessage(new WebSocketMessage("add reclamation"+ createdReclamation.getIdReclamation()));
        return ResponseEntity.ok(createdReclamation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reclamation> updateReclamation(
            @PathVariable("id") Long id,
            @RequestBody Reclamation reclamation,
    @AuthenticationPrincipal KeycloakAuthenticationToken authenticationToken) {
            Reclamation updatedReclamation = reclamationService.updateReclamation(id, reclamation);
            webSocketController.sendMessage(new WebSocketMessage("update reclamation"+ updatedReclamation.getIdReclamation()));
            return ResponseEntity.ok(updatedReclamation);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReclamation(
            @PathVariable("id") Long id,
            @AuthenticationPrincipal KeycloakAuthenticationToken authenticationToken) {

            reclamationService.deleteReclamation(id);
            webSocketController.sendMessage(new WebSocketMessage("delete reclamation"+ id));

        return ResponseEntity.ok().build();
    }


}
