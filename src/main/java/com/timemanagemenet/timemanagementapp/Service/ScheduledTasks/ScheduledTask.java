package com.timemanagemenet.timemanagementapp.Service.ScheduledTasks;
import com.timemanagemenet.timemanagementapp.Service.Employee.EmployeeService;
import com.timemanagemenet.timemanagementapp.Service.Leave.LeaveService;
import com.timemanagemenet.timemanagementapp.Service.Reclamation.ReclamationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class ScheduledTask {

    @Autowired
    private ReclamationService reclamationService;
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private LeaveService leaveService;

    // Run the task every  72 hours
    @Scheduled(fixedRate = 72 * 60 * 60 * 1000)
    @Transactional
    public void deleteOldReclamations() {
        reclamationService.deleteOldReclamations();
    }

    // Run the task every 1st day of the month
    @Scheduled(cron = "0 0 0 1 * ?")
    public void updateTotalLeave() {
        employeeService.updateTotalLeaveDays();
    }

    // Run the task every 1st day of the year
    @Scheduled(cron = "0 0 0 1 1 ?")
    public void resetUsedLeave() {
        employeeService.resetUsedLeaves();
    }

    @Scheduled(fixedRate = 72 * 60 * 60 * 1000)
    @Transactional

    public void markRefusedLeavesAsDeleted() {
        leaveService.markRefusedLeavesAsDeleted();
    }
}