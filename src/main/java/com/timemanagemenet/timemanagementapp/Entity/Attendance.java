package com.timemanagemenet.timemanagementapp.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;

@AllArgsConstructor
@Table(name="ATTENDANCE")
@SQLDelete(sql = "UPDATE  ATTENDANCE SET is_deleted = 1 where=?")
@Where(clause = "is_deleted=0")
@NoArgsConstructor
@Entity
@Data
public class Attendance implements Serializable {
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

    @Column(name = "is_deleted")
    private Integer isDeleted;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @Column(name = "updated_by")
    private String updatedBy;


}
