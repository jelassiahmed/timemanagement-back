package com.timemanagemenet.timemanagementapp.Service.Attendance;

import com.timemanagemenet.timemanagementapp.Entity.Attendance;
import com.timemanagemenet.timemanagementapp.Repository.AttendanceRepository;
import javassist.NotFoundException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AttendanceServiceImpl implements AttendanceService {
    private final AttendanceRepository attendanceRepository;

    @Autowired
    public AttendanceServiceImpl(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }
    @Override
    public List<Attendance> getAllAttendances() {
        return attendanceRepository.findAll();
    }
    @Override
    public List<Attendance> getAttendancesByUserId(String userId) {
        return attendanceRepository.findByKeycloakUserId(userId);
    }

    @Override
    public Attendance getAttendanceById(Long attendanceId) throws NotFoundException {
        return attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new NotFoundException("Attendance not found"));
    }

    public Attendance createAttendance(Attendance attendance) {
        attendance.setCreatedAt(LocalDateTime.now());
        attendance.setUpdatedAt(LocalDateTime.now());
        attendance.setIsDeleted(0);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof org.keycloak.KeycloakPrincipal<?> keycloakPrincipal) {
            String createdBy = keycloakPrincipal.getName();
            attendance.setCreatedBy(createdBy);
            attendance.setUpdatedBy(createdBy);
        }

        return attendanceRepository.save(attendance);
    }
    @Override
    public Attendance updateAttendance(Long attendanceId, Attendance updatedAttendance) throws NotFoundException {
        Optional<Attendance> existingAttendance = attendanceRepository.findById(attendanceId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (existingAttendance.isPresent()) {
            Attendance attendance = existingAttendance.get();
            attendance.setUpdatedAt(LocalDateTime.now());
            if(authentication != null && authentication.getPrincipal() instanceof org.keycloak.KeycloakPrincipal<?> keycloakPrincipal) {
                String updatedBy = keycloakPrincipal.getName();
                attendance.setUpdatedBy(updatedBy);
            }
            attendance.setAttendanceType(updatedAttendance.getAttendanceType());
            attendance.setInputType(updatedAttendance.getInputType());
            attendance.setAttendanceTime(updatedAttendance.getAttendanceTime());
            attendance.setAttendanceDate(updatedAttendance.getAttendanceDate());

            return attendanceRepository.save(attendance);
        } else {
            throw new NotFoundException("Attendance not found");
        }
    }

    public void deleteAttendance(Long attendanceId) {
        attendanceRepository.deleteById(attendanceId);
    }
}

