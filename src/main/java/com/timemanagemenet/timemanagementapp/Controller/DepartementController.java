package com.timemanagemenet.timemanagementapp.Controller;

import com.timemanagemenet.timemanagementapp.Entity.Departement;
import com.timemanagemenet.timemanagementapp.Entity.WebSocketMessage;
import com.timemanagemenet.timemanagementapp.Service.Departement.DepartementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/departments")
public class DepartementController {
    private final DepartementService departementService;
    final WebSocketController webSocketController;

    @Autowired
    public DepartementController(DepartementService departementService, WebSocketController webSocketController) {
        this.departementService = departementService;
        this.webSocketController = webSocketController;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Departement>> getAllDepartements() {
        List<Departement> departements = departementService.getAllDepartements();
        return new ResponseEntity<>(departements, HttpStatus.OK);
    }

    @GetMapping("/{departementId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Departement> getDepartementById(@PathVariable Long departementId) {
        Departement departement = departementService.getDepartementById(departementId);
        if (departement != null) {
            return new ResponseEntity<>(departement, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Departement> createDepartement(@RequestBody Departement departement) {
        Departement createdDepartement = departementService.createDepartement(departement);
        webSocketController.sendMessage(new WebSocketMessage("added departement"+ createdDepartement.getDepartementId()));
        return new ResponseEntity<>(createdDepartement, HttpStatus.CREATED);
    }

    @PutMapping("/{departementId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Departement> updateDepartement(@PathVariable Long departementId, @RequestBody Departement updatedDepartement) {
        Departement updated = departementService.updateDepartement(departementId, updatedDepartement);
        if (updated != null) {
            webSocketController.sendMessage(new WebSocketMessage("updated departement"+ updated.getDepartementId()));
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{departementId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteDepartement(@PathVariable Long departementId) {
        departementService.deleteDepartement(departementId);
        webSocketController.sendMessage(new WebSocketMessage("deleted departement"+ departementId));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
