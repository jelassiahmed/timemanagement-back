package com.timemanagemenet.timemanagementapp.Controller;

import com.timemanagemenet.timemanagementapp.Entity.Schedule;
import com.timemanagemenet.timemanagementapp.Service.Schedule.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Schedule>> getAllSchedules() {
        List<Schedule> schedules = scheduleService.getAllSchedules();
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

    @GetMapping("/{scheduleId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Schedule> getScheduleById(@PathVariable Long scheduleId) {
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
        Schedule createdSchedule = scheduleService.createSchedule(schedule);
        return new ResponseEntity<>(createdSchedule, HttpStatus.CREATED);
    }

    @PutMapping("/{scheduleId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Schedule> updateSchedule(@PathVariable Long scheduleId, @RequestBody Schedule schedule) {
        Schedule updatedSchedule = scheduleService.updateSchedule(scheduleId, schedule);
        if (updatedSchedule != null) {
            return new ResponseEntity<>(updatedSchedule, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{scheduleId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long scheduleId) {
        scheduleService.deleteSchedule(scheduleId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
