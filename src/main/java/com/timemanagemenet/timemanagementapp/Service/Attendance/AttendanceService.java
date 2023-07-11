package com.timemanagemenet.timemanagementapp.Service.Attendance;

import com.timemanagemenet.timemanagementapp.Entity.Attendance;
import javassist.NotFoundException;

import java.util.List;

public interface AttendanceService {
    List<Attendance> getAllAttendances();
    List<Attendance> getAttendancesByUserId(String userId);
    Attendance getAttendanceById(Long attendanceId) throws NotFoundException;
    Attendance createAttendance(Attendance attendance);
    Attendance updateAttendance(Long attendanceId, Attendance updatedAttendance) throws NotFoundException;
    void deleteAttendance(Long attendanceId);
}
