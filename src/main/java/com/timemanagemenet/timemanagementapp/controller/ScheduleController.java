package com.timemanagemenet.timemanagementapp.controller;

import com.timemanagemenet.timemanagementapp.entity.Schedule;
import com.timemanagemenet.timemanagementapp.entity.WebSocketMessage;
import com.timemanagemenet.timemanagementapp.service.schedule.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;
    private final WebSocketController webSocketController;

    @Autowired
    public ScheduleController(ScheduleService scheduleService, WebSocketController webSocketController) {
        this.scheduleService = scheduleService;
        this.webSocketController = webSocketController;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Schedule>> getAllSchedules() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));

        if (!isAdmin) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        List<Schedule> schedules = scheduleService.getAllSchedules();
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

    @GetMapping("/{scheduleId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Schedule> getScheduleById(@PathVariable Long scheduleId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));

        if (!isAdmin) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Schedule schedule = scheduleService.getScheduleById(scheduleId);
        if (schedule != null) {
            return new ResponseEntity<>(schedule, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Schedule> createSchedule(@RequestBody Schedule schedule) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));

        if (!isAdmin) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Schedule createdSchedule = scheduleService.createSchedule(schedule);
        webSocketController.sendMessage(new WebSocketMessage("newSchedule", createdSchedule.getScheduleId()));
        return new ResponseEntity<>(createdSchedule, HttpStatus.CREATED);
    }

    @PutMapping("/{scheduleId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Schedule> updateSchedule(@PathVariable Long scheduleId, @RequestBody Schedule schedule) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));

        if (!isAdmin) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Schedule updatedSchedule = scheduleService.updateSchedule(scheduleId, schedule);
        if (updatedSchedule != null) {
            webSocketController.sendMessage(new WebSocketMessage("updateSchedule", updatedSchedule.getScheduleId()));
            return new ResponseEntity<>(updatedSchedule, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{scheduleId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long scheduleId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));

        if (!isAdmin) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        scheduleService.deleteSchedule(scheduleId);
        webSocketController.sendMessage(new WebSocketMessage("deleteSchedule", scheduleId));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
