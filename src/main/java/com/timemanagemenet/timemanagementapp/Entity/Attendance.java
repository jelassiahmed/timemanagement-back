package com.timemanagemenet.timemanagementapp.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Attendance {
    @Id
    @Column(name= "attendance_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAttendance;

    //chekin checkout
    private String attendanceType ;

    //card finger password
    private String inputType;

    private int attendanceTime;

    private LocalDate attendanceDate;

    @Column(name = "keycloak_user_id")
    private String keycloakUserId;


}
