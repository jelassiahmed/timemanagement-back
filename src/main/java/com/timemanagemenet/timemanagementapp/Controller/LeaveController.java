package com.timemanagemenet.timemanagementapp.Controller;

import com.timemanagemenet.timemanagementapp.Entity.Leave;
import com.timemanagemenet.timemanagementapp.Service.Leave.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/leaves")
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    @GetMapping
    public List<Leave> getAllLeaves() {
        return leaveService.getAllLeaves();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Leave> getLeaveById(@PathVariable Long id) {
        Optional<Leave> leave = leaveService.getLeaveById(id);
        return leave.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Leave> createLeave(@RequestBody Leave leave) {
        Leave createdLeave = leaveService.createLeave(leave);
        return new ResponseEntity<>(createdLeave, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Leave> updateLeave(@PathVariable Long id, @RequestBody Leave leave) {
        try {
            Leave updatedLeave = leaveService.updateLeave(id, leave);
            return new ResponseEntity<>(updatedLeave, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLeave(@PathVariable Long id) {
        leaveService.deleteLeave(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
