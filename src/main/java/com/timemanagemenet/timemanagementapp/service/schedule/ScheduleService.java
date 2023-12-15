package com.timemanagemenet.timemanagementapp.service.schedule;

import com.timemanagemenet.timemanagementapp.entity.Schedule;

import java.util.List;

public interface ScheduleService {
    List<Schedule> getAllSchedules();

    Schedule getScheduleById(Long scheduleId);

    Schedule createSchedule(Schedule schedule);

    Schedule updateSchedule(Long scheduleId, Schedule schedule);

    void deleteSchedule(Long scheduleId);
}
