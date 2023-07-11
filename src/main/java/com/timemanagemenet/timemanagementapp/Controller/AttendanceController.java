package com.timemanagemenet.timemanagementapp.Controller;

import com.timemanagemenet.timemanagementapp.Entity.Attendance;
import com.timemanagemenet.timemanagementapp.Service.Attendance.AttendanceService;
import javassist.NotFoundException;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {
    private final AttendanceService attendanceService;

    @Autowired
    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Attendance>> getAllAttendances() {
        List<Attendance> attendances = attendanceService.getAllAttendances();
        return ResponseEntity.ok(attendances);
    }

    @GetMapping("/{attendanceId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Attendance> getAttendanceById(@PathVariable Long attendanceId) throws NotFoundException {
        Attendance attendance = attendanceService.getAttendanceById(attendanceId);
        return ResponseEntity.ok(attendance);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Attendance>> getAttendancesByUserId(
            @PathVariable String userId,
            @AuthenticationPrincipal KeycloakAuthenticationToken authenticationToken) {
        if (authenticationToken != null && userId.equals(authenticationToken.getName())) {
            List<Attendance> attendances = attendanceService.getAttendancesByUserId(userId);
            return ResponseEntity.ok(attendances);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Attendance> createAttendance(@RequestBody Attendance attendance) {
        Attendance createdAttendance = attendanceService.createAttendance(attendance);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAttendance);
    }

    @PutMapping("/{attendanceId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Attendance> updateAttendance(
            @PathVariable Long attendanceId,
            @RequestBody Attendance updatedAttendance) throws NotFoundException {
        Attendance updated = attendanceService.updateAttendance(attendanceId, updatedAttendance);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{attendanceId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteAttendance(@PathVariable Long attendanceId) {
        attendanceService.deleteAttendance(attendanceId);
        return ResponseEntity.noContent().build();
    }
}

