package com.timemanagemenet.timemanagementapp;

import com.timemanagemenet.timemanagementapp.entity.Employee;
import com.timemanagemenet.timemanagementapp.entity.Leave;
import com.timemanagemenet.timemanagementapp.repository.EmployeeRepository;
import com.timemanagemenet.timemanagementapp.repository.LeaveRepository;
import com.timemanagemenet.timemanagementapp.service.leave.LeaveServiceImpl;
import com.timemanagemenet.timemanagementapp.service.workflow.WorkflowService;
import org.camunda.bpm.engine.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.keycloak.KeycloakPrincipal;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class LeaveServiceImplTest {

    @Mock
    private LeaveRepository leaveRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private WorkflowService workflowService;

    @Mock
    private TaskService taskService;

    @InjectMocks
    private LeaveServiceImpl leaveService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    /*
    @Test
    void testRequestLeave() {
        // Arrange
        Employee employee = new Employee();
        employee.setUserName("testUser");
        employee.setTotalLeave(20);
        employee.setUsedLeave(5);

        Leave leave = new Leave();
        leave.setStartDate(LocalDate.now());
        leave.setEndDate(LocalDate.now().plus(5, ChronoUnit.DAYS));

        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(new KeycloakPrincipal<>(null, null));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        when(leaveRepository.save(any(Leave.class))).thenReturn(leave);
        when(employeeRepository.findByUserName(eq("testUser"))).thenReturn(employee);

        // Act
        Leave result = leaveService.requestLeave(employee, leave);

        // Assert
        verify(leaveRepository, times(1)).save(any(Leave.class));
        verify(employeeRepository, times(1)).findByUserName(eq("testUser"));
        verify(workflowService, times(1)).startProcessByInstId(any(Leave.class), anyString());

        assertEquals(0, result.getStatus()); // Verify the status is set correctly
        assertEquals(6, result.getNumberOfDays()); // Adjust based on your business logic
    }

    @Test
    void testApproveLeave() {
        // Arrange
        Leave leave = new Leave();
        leave.setStatus(0);
        leave.setNumberOfDays(3);
        leave.setKeycloakUserId("testUser");

        Employee employee = new Employee();
        employee.setUserName("testUser");
        employee.setUsedLeave(5);

        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(new KeycloakPrincipal<>(null, null));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        when(leaveRepository.save(any(Leave.class))).thenReturn(leave);
        when(employeeRepository.findByUserName(eq("testUser"))).thenReturn(employee);

        // Act
        leaveService.approveLeave(leave);

        // Assert
        verify(leaveRepository, times(1)).save(any(Leave.class));
        verify(employeeRepository, times(1)).findByUserName(eq("testUser"));

        assertEquals(1, leave.getStatus()); // Verify the status is set to approved
        assertEquals(8, employee.getUsedLeave()); // Adjust based on your business logic
    }

     */

    // Add more test methods for other methods in LeaveServiceImpl
}
