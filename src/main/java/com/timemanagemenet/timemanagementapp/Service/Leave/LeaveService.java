package com.timemanagemenet.timemanagementapp.Service.Leave;

import com.timemanagemenet.timemanagementapp.Entity.Leave;

import java.util.List;
import java.util.Optional;

public interface LeaveService {
    List<Leave> getAllLeaves();
    Optional<Leave> getLeaveById(Long id);
    Leave createLeave(Leave leave);
    Leave updateLeave(Long id, Leave leave);
    void deleteLeave(Long id);
}