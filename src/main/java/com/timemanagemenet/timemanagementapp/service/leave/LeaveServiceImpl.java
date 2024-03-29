package com.timemanagemenet.timemanagementapp.service.leave;

import com.timemanagemenet.timemanagementapp.entity.Employee;
import com.timemanagemenet.timemanagementapp.entity.Leave;
import com.timemanagemenet.timemanagementapp.repository.EmployeeRepository;
import com.timemanagemenet.timemanagementapp.repository.LeaveRepository;
import com.timemanagemenet.timemanagementapp.service.workflow.WorkflowService;
import org.camunda.bpm.engine.TaskService;
import org.keycloak.KeycloakPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    private LeaveRepository leaveRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private WorkflowService workflowService;

    @Autowired
    private TaskService taskService;


    @Override
    public Leave requestLeave(Employee employee, Leave leave) {

        long numberOfDaysRequested = ChronoUnit.DAYS.between(leave.getStartDate(), leave.getEndDate());


        if (numberOfDaysRequested > (employee.getTotalLeave() - employee.getUsedLeave())) {
            // Exceeded available leave, mark leave as deleted (refused)
            leave.setIsDeleted(0);
            leave.setStatus(-1);
        } else {
            // Sufficient leave, mark leave as pending
            leave.setStatus(0);
            leave.setIsDeleted(0);
        }

        //calculate return date
        leave.setBackDate(leave.getEndDate().plusDays(1));
        //calculate number of days requested
        leave.setNumberOfDays((int) numberOfDaysRequested);

        leave.setCreatedAt(LocalDateTime.now());
        leave.setUpdatedAt(LocalDateTime.now());
        leave.setKeycloakUserId(employee.getUserName());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof KeycloakPrincipal<?> keycloakPrincipal) {
            String createdBy = keycloakPrincipal.getName();
            leave.setCreatedBy(createdBy);
            leave.setUpdatedBy(createdBy);
        }

        // Save the leave
        leaveRepository.save(leave);

        if(leave.getStatus() == 0)
        {workflowService.startProcessByInstId(leave, employee.getDepartement().getDepartementManagerId());}
        return leave;
    }

    @Override
    public void approveLeave(Leave leave) {
        leave.setStatus(1);

        String employee = leave.getKeycloakUserId();

        Employee employee1 = employeeRepository.findByUserName(employee);
        employee1.setUsedLeave(employee1.getUsedLeave() + leave.getNumberOfDays());

        leave.setUpdatedAt(LocalDateTime.now());
        leaveRepository.save(leave);
        employeeRepository.save(employee1);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof KeycloakPrincipal<?> keycloakPrincipal) {
            String updatedBy = keycloakPrincipal.getName();
            leave.setUpdatedBy(updatedBy);
            leave.setUpdatedAt(LocalDateTime.now());
        }

    }

    @Override
    public void rejectLeave(Leave leave) {
        leave.setStatus(-1); // Set status to rejected
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof KeycloakPrincipal<?> keycloakPrincipal) {
            String updatedBy = keycloakPrincipal.getName();
            leave.setUpdatedBy(updatedBy);
            leave.setUpdatedAt(LocalDateTime.now());
        }
        leaveRepository.save(leave);
    }

    @Override
    public Optional<Leave> findById(Long id) {
        return leaveRepository.findById(id);
    }

    @Override
    public List<Leave> getLeavesByUserId(String userId) {
        return leaveRepository.findByKeycloakUserId(userId);
    }

    @Override
    public List<Leave> getAllLeaves() {
        return leaveRepository.findAll();
    }

    @Override
    @Transactional
    public void markRefusedLeavesAsDeleted() {
        LocalDateTime threeDaysAgo = LocalDateTime.now().minusDays(3);
        leaveRepository.markAsDeleted(-1, threeDaysAgo);
    }
}






