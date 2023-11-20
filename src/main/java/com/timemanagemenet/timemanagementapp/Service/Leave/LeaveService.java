package com.timemanagemenet.timemanagementapp.Service.Leave;

import com.timemanagemenet.timemanagementapp.Entity.Employee;
import com.timemanagemenet.timemanagementapp.Entity.Leave;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface LeaveService {
    void requestLeave(Employee employee, Leave leave);
    void approveLeave(Leave leave);
    void rejectLeave(Leave leave);

    Optional<Leave> findById(Long id);

     List<Leave> getLeavesByUserId(String userId);

    List<Leave> getAllLeaves();
    @Transactional
    void markRefusedLeavesAsDeleted();

    }
