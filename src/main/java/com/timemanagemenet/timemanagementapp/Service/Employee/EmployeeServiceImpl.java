package com.timemanagemenet.timemanagementapp.Service.Employee;

import com.timemanagemenet.timemanagementapp.Entity.Credentials;
import com.timemanagemenet.timemanagementapp.Entity.Employee;
import com.timemanagemenet.timemanagementapp.Repository.EmployeeRepository;
import com.timemanagemenet.timemanagementapp.config.KeycloakConfig;
import org.camunda.bpm.model.xml.ModelBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Consumer;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    // Assuming you have a method to convert Employee to UserRepresentation

    @Autowired
    private KeycloakConfig keycloakConfig;

    @Autowired
    private EmployeeRepository employeeRepository;
    private UserRepresentation convertEmployeeToUser(Employee employee) {
        CredentialRepresentation credential = Credentials.createPasswordCredentials(employee.getPassword()); // Assuming you have a getPassword() method in Employee class
        UserRepresentation user = new UserRepresentation();
        user.setUsername(employee.getUserName());
        user.setFirstName(employee.getFirstName());
        user.setLastName(employee.getLastName());
        user.setEmail(employee.getEmail());
        user.setCredentials(Collections.singletonList(credential));
        user.setEnabled(true);

        RoleRepresentation role = new RoleRepresentation();
        role.setName("ROLE_EMPLOYEE");
        user.setRealmRoles(Collections.singletonList(role.getName()));
        return user;
    }

    @Override
    public Employee findEmployeeByUserName(String userName) {
        return employeeRepository.findByUserName(userName);
    }

    public void addEmployee(Employee employee){
        UserRepresentation user = convertEmployeeToUser(employee);

        UsersResource instance = getInstance();
        instance.create(user);
        employee.setUpdatedAt(LocalDateTime.now());
        employee.setCreatedAt(LocalDateTime.now());
        employee.setIsDeleted(0);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof org.keycloak.KeycloakPrincipal<?> keycloakPrincipal) {
            String createdBy = keycloakPrincipal.getName();
            employee.setCreatedBy(createdBy);
            employee.setUpdatedBy(createdBy);
        }
        employeeRepository.save(employee);
        System.out.println(user.getUsername() + employee.getPassword() + employee.getFirstName() + employee.getLastName() + employee.getEmail());
    }

    public List<UserRepresentation> getEmployee(String userName){
        UsersResource usersResource = getInstance();
        return usersResource.search(userName, true);
    }

    public void updateEmployee(String userId, Employee employee){
        Employee existingEmployee = employeeRepository.findByUserName(userId);
        if (existingEmployee == null) {
            return;
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof org.keycloak.KeycloakPrincipal<?> keycloakPrincipal) {
            String updatedBy = keycloakPrincipal.getName();
            existingEmployee.setUpdatedBy(updatedBy);
        }

        existingEmployee.setUpdatedAt(LocalDateTime.now());

        updateFieldIfNotNull(existingEmployee::setFirstName, employee.getFirstName());
        updateFieldIfNotNull(existingEmployee::setLastName, employee.getLastName());
        updateFieldIfNotNull(existingEmployee::setEmail, employee.getEmail());
        updateFieldIfNotNull(existingEmployee::setUserName, employee.getUserName());
        updateFieldIfNotNull(existingEmployee::setPassword, employee.getPassword());
        updateFieldIfNotNull(existingEmployee::setMobile, employee.getMobile());
        updateFieldIfNotNull(existingEmployee::setTotalLeave, employee.getTotalLeave());
        updateFieldIfNotNull(existingEmployee::setUsedLeave, employee.getUsedLeave());

        employeeRepository.save(existingEmployee);


    }

    private <T> void updateFieldIfNotNull(Consumer<T> setter, T value) {
        if (value != null) {
            setter.accept(value);
        }
    }

    public void deleteEmployee(String userId){
        UsersResource usersResource = getInstance();
        usersResource.get(userId).remove();
    }

    public void sendVerificationLink(String userId){
        UsersResource usersResource = getInstance();
        usersResource.get(userId).sendVerifyEmail();
    }

    public void sendResetPassword(String userId){
        UsersResource usersResource = getInstance();
        usersResource.get(userId).executeActionsEmail(Arrays.asList("UPDATE_PASSWORD"));
    }

    public UsersResource getInstance(){
        return keycloakConfig.keycloak().realm("TimeManagement").users();
    }
}
