package com.timemanagemenet.timemanagementapp.Controller;

import com.timemanagemenet.timemanagementapp.Entity.Employee;
import com.timemanagemenet.timemanagementapp.Entity.Leave;
import com.timemanagemenet.timemanagementapp.Entity.dto.Notification;
import com.timemanagemenet.timemanagementapp.Service.Employee.EmployeeService;
import com.timemanagemenet.timemanagementapp.Service.Leave.LeaveService;
import com.timemanagemenet.timemanagementapp.Service.Workflow.WorkflowService;
import org.camunda.bpm.engine.task.Task;
import org.keycloak.KeycloakPrincipal;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/leaves")
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    @Autowired
    private WorkflowService workflowService;

    @Autowired
    private EmployeeService employeeService;

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(LeaveController.class);
    @PostMapping("/request")
    public ResponseEntity<String> requestLeave(@RequestBody Leave leaveRequestDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof KeycloakPrincipal<?> keycloakPrincipal) {
            String userName = keycloakPrincipal.getName();

            // Retrieve the employee based on userName
            Optional<Employee> employeeOptional = Optional.ofNullable(employeeService.findEmployeeByUserName(userName));

            if (employeeOptional.isPresent()) {
                Employee employee = employeeOptional.get();
                Leave leave = new Leave();

                leave.setLeaveType(leaveRequestDTO.getLeaveType());
                leave.setStartDate(leaveRequestDTO.getStartDate());
                leave.setEndDate(leaveRequestDTO.getEndDate());
                leave.setInterimUser(leaveRequestDTO.getInterimUser());

                leaveService.requestLeave(employee, leave);

                return ResponseEntity.ok("Leave request submitted successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access.");
        }
    }

    @GetMapping("/check-process-id")
    public void checkProcessId(String name){

       String procId= workflowService.getProcessId(name);

        logger.info("process id is "+procId);
    }

    @PostMapping("/approve/{id}")
    public ResponseEntity<String> approveLeave(@PathVariable Long id) {
        Optional<Leave> leaveOptional = leaveService.findById(id);

        if (leaveOptional.isPresent()) {
            Leave leave = leaveOptional.get();
            leaveService.approveLeave(leave);
            return ResponseEntity.ok("Leave request approved successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Leave request not found.");
        }
    }

    @PostMapping("/reject/{id}")
    public ResponseEntity<String> rejectLeave(@PathVariable Long id) {
        Optional<Leave> leaveOptional = leaveService.findById(id);

        if (leaveOptional.isPresent()) {
            Leave leave = leaveOptional.get();
            leaveService.rejectLeave(leave);
            return ResponseEntity.ok("Leave request rejected successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Leave request not found.");
        }
    }

    @GetMapping("/{userId}")
    public List<Leave> getLeavesByUserId(@PathVariable String userId) {
        return leaveService.getLeavesByUserId(userId);
    }

    @GetMapping
    public List<Leave> getAllLeaves() {
        return leaveService.getAllLeaves();
    }

    @GetMapping("/tasks") List<Notification> getNotifs(){
        return workflowService.loadNotification("admin_user");
    }

}
