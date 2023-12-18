package com.timemanagemenet.timemanagementapp.entity;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@AllArgsConstructor
@Table(name="ATTENDANCE")
@SQLDelete(sql = "UPDATE  ATTENDANCE SET is_deleted = 1 where attendance_id =?")
@Where(clause = "is_deleted=0")
@NoArgsConstructor
@Entity
@Getter
@Setter
@ToString
public class Attendance implements Serializable {
    @Id
    @Column(name= "attendance_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAttendance;

    //checkin checkout
    private String attendanceType ;

    //card finger password
    private String inputType;

    private int attendanceTime;

    private LocalDate attendanceDate;

    @Column(name = "keycloak_user_id")
    private String keycloakUserId;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Attendance that = (Attendance) o;
        return getIdAttendance() != null && Objects.equals(getIdAttendance(), that.getIdAttendance());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
