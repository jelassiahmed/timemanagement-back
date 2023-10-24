package com.timemanagemenet.timemanagementapp.Controller;

import com.timemanagemenet.timemanagementapp.Entity.Diplome;
import com.timemanagemenet.timemanagementapp.Service.Diplome.DiplomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("diplomes")
public class DiplomeController {

    @Autowired
    private DiplomeService diplomeService;

    @GetMapping("/{id}")
    public Diplome getDiplomeById(@PathVariable Long id) {
        return diplomeService.getDiplomeById(id);
    }

    @PostMapping
    public void saveDiplome(@RequestBody Diplome diplome) {
        diplomeService.saveDiplome(diplome);
    }

    @PutMapping("/{id}")
    public void updateDiplome(@PathVariable Long id, @RequestBody Diplome updatedDiplome) {
        diplomeService.updateDiplome(id, updatedDiplome);
    }

    @DeleteMapping("/{id}")
    public void deleteDiplome(@PathVariable Long id) {
        diplomeService.deleteDiplome(id);
    }

    @GetMapping("/user/{id}")
    public List<Diplome> getDiplomeByUserId(@PathVariable Long id) {
        return diplomeService.getDiplomeByUserId(id);
    }
}
