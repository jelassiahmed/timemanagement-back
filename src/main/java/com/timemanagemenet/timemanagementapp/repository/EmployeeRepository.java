package com.timemanagemenet.timemanagementapp.repository;

import com.timemanagemenet.timemanagementapp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findByUserName(String userName);
    @Modifying
    @Query("UPDATE Employee e SET e.totalLeave = e.totalLeave + :additionalLeave")
    void updateTotalLeave(double additionalLeave);

    @Modifying
    @Query("UPDATE Employee e SET e.usedLeave = 0")
    void resetUsedLeave();
}
