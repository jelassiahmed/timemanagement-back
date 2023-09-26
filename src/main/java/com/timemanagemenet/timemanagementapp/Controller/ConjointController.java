package com.timemanagemenet.timemanagementapp.Controller;

import com.timemanagemenet.timemanagementapp.Entity.Conjoint;
import com.timemanagemenet.timemanagementapp.Service.Conjoint.ConjointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/conjoints")
public class ConjointController {

    @Autowired
    private ConjointService conjointService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Conjoint> saveConjoint(@RequestBody Conjoint conjoint) {
        Conjoint savedConjoint = conjointService.saveConjoint(conjoint);
        return ResponseEntity.ok(savedConjoint);
    }

    @GetMapping("/{employeeId}/{conjointId}")
    public ResponseEntity<Conjoint> getConjointById(@PathVariable Long employeeId, @PathVariable Integer conjointId) {
        Conjoint conjoint = conjointService.getConjointById(employeeId, conjointId);
        return ResponseEntity.ok(conjoint);
    }

    @DeleteMapping("/{employeeId}/{conjointId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteConjointById(@PathVariable Long employeeId, @PathVariable Integer conjointId) {
        conjointService.deleteConjointById(employeeId, conjointId);
        return ResponseEntity.ok("Conjoint deleted successfully");
    }
}