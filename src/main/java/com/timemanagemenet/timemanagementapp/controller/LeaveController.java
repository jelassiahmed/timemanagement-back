package com.timemanagemenet.timemanagementapp.controller;

import com.timemanagemenet.timemanagementapp.entity.Employee;
import com.timemanagemenet.timemanagementapp.entity.Leave;
import com.timemanagemenet.timemanagementapp.entity.WebSocketMessage;
import com.timemanagemenet.timemanagementapp.entity.dto.Notification;
import com.timemanagemenet.timemanagementapp.service.employee.EmployeeService;
import com.timemanagemenet.timemanagementapp.service.leave.LeaveService;
import com.timemanagemenet.timemanagementapp.service.workflow.WorkflowService;
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
    @Autowired
    private WebSocketController webSocketController;

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
               Leave createdLeave =  leaveService.requestLeave(employee, leave);
                webSocketController.sendMessage(new WebSocketMessage("New leave request",  createdLeave.getIdLeave()));

                return ResponseEntity.ok("Leave request submitted successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access.");
        }
    }

    @PostMapping("/approve/{id}")
    public ResponseEntity<String> approveLeave(@PathVariable Long id) {
        Optional<Leave> leaveOptional = leaveService.findById(id);

        if (leaveOptional.isPresent()) {
            Leave leave = leaveOptional.get();
            leaveService.approveLeave(leave);
            webSocketController.sendMessage(new WebSocketMessage("Leave request approved",  leave.getIdLeave()));
            return ResponseEntity.ok("Leave request approved successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Leave request not found.");
        }
    }

    @PostMapping("/reject/{id}/{taskId}")
    public ResponseEntity<String> rejectLeave(@PathVariable Long id, @PathVariable String taskId) {
        Optional<Leave> leaveOptional = leaveService.findById(id);

        if (leaveOptional.isPresent()) {
            Leave leave = leaveOptional.get();
            workflowService.completeTask(taskId, "rejet");
            leaveService.rejectLeave(leave);
            webSocketController.sendMessage(new WebSocketMessage("Leave request rejected",  leave.getIdLeave()));
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
    @PostMapping ("/tasks/{taskId}") public void completeTask(@PathVariable String taskId){
        workflowService.completeTask(taskId, "accept");
        webSocketController.sendMessage(new WebSocketMessage("Leave request approved",  taskId));
    }
}
