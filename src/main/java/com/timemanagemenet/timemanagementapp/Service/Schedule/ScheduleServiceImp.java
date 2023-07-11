package com.timemanagemenet.timemanagementapp.Service.Schedule;

import com.timemanagemenet.timemanagementapp.Entity.Schedule;
import com.timemanagemenet.timemanagementapp.Repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImp implements ScheduleService {
    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleServiceImp(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    @Override
    public Schedule getScheduleById(Long scheduleId) {
        return scheduleRepository.findById(scheduleId).orElse(null);
    }

    @Override
    public Schedule createSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    @Override
    public Schedule updateSchedule(Long scheduleId, Schedule schedule) {
        Schedule existingSchedule = scheduleRepository.findById(scheduleId).orElse(null);
        if (existingSchedule != null) {
            // Update the properties of the existing schedule
            existingSchedule.setStartHour(schedule.getStartHour());
            existingSchedule.setEndHour(schedule.getEndHour());
            existingSchedule.setPauseTime(schedule.isPauseTime());
            existingSchedule.setPauseStart(schedule.getPauseStart());
            existingSchedule.setPauseEnd(schedule.getPauseEnd());
            existingSchedule.setPlannings(schedule.getPlannings());

            return scheduleRepository.save(existingSchedule);
        }
        return null;
    }

    @Override
    public void deleteSchedule(Long scheduleId) {
        scheduleRepository.deleteById(scheduleId);
    }
}
