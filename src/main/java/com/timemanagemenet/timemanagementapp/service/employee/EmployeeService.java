package com.timemanagemenet.timemanagementapp.service.employee;

import com.timemanagemenet.timemanagementapp.entity.Employee;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;

public interface EmployeeService {

    Employee findEmployeeByUserName(String userName);
    Employee addEmployee(Employee employee);

    List<UserRepresentation> getEmployee(String userName);

    void updateEmployee(String userId, Employee employee);

    void deleteEmployee(String userId);

    void sendVerificationLink(String userId);

    void sendResetPassword(String userId);

    UsersResource getInstance();

    void resetPassword(String userId, String rawPassword);

    List<Employee> getAllEmployees();
     void updateTotalLeaveDays();
     void resetUsedLeaves();

    }
