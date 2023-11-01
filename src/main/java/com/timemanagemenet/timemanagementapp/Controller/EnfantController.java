package com.timemanagemenet.timemanagementapp.Controller;

import com.timemanagemenet.timemanagementapp.Entity.Enfant;
import com.timemanagemenet.timemanagementapp.Entity.EnfantId;
import com.timemanagemenet.timemanagementapp.Service.Enfant.EnfantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enfants")
public class EnfantController {

    @Autowired
    private EnfantService enfantService;

    @GetMapping
    public List<Enfant> getAllEnfants() {
        return enfantService.getAllEnfants();
    }

    @GetMapping("/{id}")
    public Enfant getEnfantById(@PathVariable EnfantId id) {
        return enfantService.getEnfantById(id);
    }

    @PostMapping
    public void saveEnfant(@RequestBody Enfant enfant) {
        enfantService.saveEnfant(enfant);
    }

    @DeleteMapping("/{id}")
    public void deleteEnfant(@PathVariable EnfantId id) {
        enfantService.deleteEnfant(id);
    }

    @GetMapping("/employee/{employeeId}")
    public List<Enfant> getEnfantsByEmployeeId(@PathVariable Long employeeId) {
        return  enfantService.getEnfantByFatherId(employeeId);
    }
}
