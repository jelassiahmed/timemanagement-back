package com.timemanagemenet.timemanagementapp.service.attendance;

import com.timemanagemenet.timemanagementapp.entity.Attendance;
import com.timemanagemenet.timemanagementapp.repository.AttendanceRepository;
import com.timemanagemenet.timemanagementapp.service.BaseService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        BaseService.initializeEntity(attendance);

        return attendanceRepository.save(attendance);
    }
    @Override
    public Attendance updateAttendance(Long attendanceId, Attendance updatedAttendance) throws NotFoundException {
        Optional<Attendance> existingAttendance = attendanceRepository.findById(attendanceId);
        if (existingAttendance.isPresent()) {
            Attendance attendance = existingAttendance.get();
            BaseService.updateEntity(attendance, updatedAttendance);
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

