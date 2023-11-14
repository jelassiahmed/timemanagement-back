package com.timemanagemenet.timemanagementapp.Controller;

import com.timemanagemenet.timemanagementapp.Entity.Employee;
import com.timemanagemenet.timemanagementapp.Entity.ResetPasswordRequest;
import com.timemanagemenet.timemanagementapp.Service.Employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    EmployeeService service;
    @PostMapping
    public String addUser(@RequestBody Employee userDTO){
        service.addEmployee(userDTO);
        return "User Added Successfully.";
    }

    @GetMapping(path = "/{userName}")
    public Employee getUser(@PathVariable("userName") String userName){
        return service.findEmployeeByUserName(userName);
    }

    @PutMapping(path = "/update/{userId}")
    public String updateUser(@PathVariable("userId") String userId,   @RequestBody Employee userDTO){
        service.updateEmployee(userId, userDTO);
        return "User Details Updated Successfully.";
    }

    @DeleteMapping(path = "/{userId}")
    public String deleteUser(@PathVariable("userId") String userId){
        service.deleteEmployee(userId);
        return "User Deleted Successfully.";
    }

    @GetMapping(path = "/verification-link/{userId}")
    public String sendVerificationLink(@PathVariable("userId") String userId){
        service.sendVerificationLink(userId);
        return "Verification Link Send to Registered E-mail Id.";
    }

    @PutMapping("/{userId}/reset-password")
    public ResponseEntity<String> resetPassword(@PathVariable String userId, @RequestBody ResetPasswordRequest request) {
        try {
            service.resetPassword(userId, request.getRawPassword());
            return ResponseEntity.ok("Password reset successful.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error resetting password: " + e.getMessage());
        }
    }
    @GetMapping("/all")
    public List<Employee> getAllUsers(){
        return service.getAllEmployees();
    }
}