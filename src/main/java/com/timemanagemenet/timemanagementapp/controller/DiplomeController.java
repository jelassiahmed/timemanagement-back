package com.timemanagemenet.timemanagementapp.controller;

import com.timemanagemenet.timemanagementapp.entity.Diplome;
import com.timemanagemenet.timemanagementapp.entity.WebSocketMessage;
import com.timemanagemenet.timemanagementapp.service.diplome.DiplomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("diplomes")
public class DiplomeController {

    @Autowired
    private DiplomeService diplomeService;

    @Autowired
    private WebSocketController webSocketController;

    @GetMapping("/{id}")
    public Diplome getDiplomeById(@PathVariable Long id) {
        return diplomeService.getDiplomeById(id);
    }

    @PostMapping
    public void saveDiplome(@RequestBody Diplome diplome) {

       Diplome newDip = diplomeService.saveDiplome(diplome);
         webSocketController.sendMessage(new WebSocketMessage("add diplome"+ newDip.getIdDiplome()));
    }

    @PutMapping("/{id}")
    public void updateDiplome(@PathVariable Long id, @RequestBody Diplome updatedDiplome) {
      Diplome updatedDip =  diplomeService.updateDiplome(id, updatedDiplome);
        webSocketController.sendMessage(new WebSocketMessage("update diplome"+ updatedDip.getIdDiplome()));
    }

    @DeleteMapping("/{id}")
    public void deleteDiplome(@PathVariable Long id) {

        diplomeService.deleteDiplome(id);
        webSocketController.sendMessage(new WebSocketMessage("delete diplome"+ id));
    }

    @GetMapping("/user/{id}")
    public List<Diplome> getDiplomeByUserId(@PathVariable Long id) {
        return diplomeService.getDiplomeByUserId(id);
    }
}
