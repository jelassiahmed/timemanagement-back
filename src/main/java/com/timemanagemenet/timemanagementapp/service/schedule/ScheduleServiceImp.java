package com.timemanagemenet.timemanagementapp.service.schedule;

import com.timemanagemenet.timemanagementapp.entity.Schedule;
import com.timemanagemenet.timemanagementapp.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        schedule.setCreatedAt(LocalDateTime.now());
        schedule.setUpdatedAt(LocalDateTime.now());
        schedule.setIsDeleted(0);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof org.keycloak.KeycloakPrincipal<?> keycloakPrincipal) {
            String createdBy = keycloakPrincipal.getName();
            schedule.setCreatedBy(createdBy);
            schedule.setUpdatedBy(createdBy);
        }
        return scheduleRepository.save(schedule);
    }

    @Override
    public Schedule updateSchedule(Long scheduleId, Schedule schedule) {
        Schedule existingSchedule = scheduleRepository.findById(scheduleId).orElse(null);
        schedule.setUpdatedAt(LocalDateTime.now());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (existingSchedule != null) {
            // Update the properties of the existing schedule
            if(authentication != null && authentication.getPrincipal() instanceof org.keycloak.KeycloakPrincipal<?> keycloakPrincipal) {
                String updatedBy = keycloakPrincipal.getName();
                schedule.setUpdatedBy(updatedBy);
            }
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
