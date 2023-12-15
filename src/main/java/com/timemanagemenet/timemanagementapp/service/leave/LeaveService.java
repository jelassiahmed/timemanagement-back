package com.timemanagemenet.timemanagementapp.service.leave;

import com.timemanagemenet.timemanagementapp.entity.Employee;
import com.timemanagemenet.timemanagementapp.entity.Leave;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface LeaveService {
    Leave requestLeave(Employee employee, Leave leave);
    void approveLeave(Leave leave);
    void rejectLeave(Leave leave);

    Optional<Leave> findById(Long id);

     List<Leave> getLeavesByUserId(String userId);

    List<Leave> getAllLeaves();
    @Transactional
    void markRefusedLeavesAsDeleted();

    }
