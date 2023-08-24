package com.timemanagemenet.timemanagementapp.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE  LEAVE SET is_deleted = 1 where id_leave=?")
@Table(name="LEAVE")
@Where(clause = "is_deleted=0")
@Entity
@Data
public class Leave implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_leave", nullable = false)
    private Long idLeave;

    @Column(name = "leave_type", nullable = false)
    private String leaveType;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "back_date", nullable = false)
    private LocalDateTime backDate;

    @Column(name = "number_of_days", nullable = false)
    private Integer numberOfDays;

    @Column(name = "interim_user_id", nullable = false)
    private String interimUser;

    @Column(name = "keycloak_user_id", nullable = false)
    private String keycloakUserId;


}
