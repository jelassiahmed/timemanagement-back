package com.timemanagemenet.timemanagementapp.Service.Attendance;

import com.timemanagemenet.timemanagementapp.Entity.Attendance;
import com.timemanagemenet.timemanagementapp.Repository.AttendanceRepository;
import javassist.NotFoundException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return attendanceRepository.save(attendance);
    }
    @Override
    public Attendance updateAttendance(Long attendanceId, Attendance updatedAttendance) throws NotFoundException {
        Attendance attendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new NotFoundException("Attendance not found"));

        // Update the fields of the existing attendance object
        attendance.setAttendanceType(updatedAttendance.getAttendanceType());
        attendance.setInputType(updatedAttendance.getInputType());
        attendance.setAttendanceTime(updatedAttendance.getAttendanceTime());
        attendance.setAttendanceDate(updatedAttendance.getAttendanceDate());

        return attendanceRepository.save(attendance);
    }

    public void deleteAttendance(Long attendanceId) {
        attendanceRepository.deleteById(attendanceId);
    }
}

