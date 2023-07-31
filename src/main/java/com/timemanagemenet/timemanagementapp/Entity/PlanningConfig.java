package com.timemanagemenet.timemanagementapp.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "planning_config")
@SQLDelete(sql = "UPDATE  planning_config SET is_deleted = 1 where=?")
@Where(clause = "is_deleted=0")
@AllArgsConstructor
@NoArgsConstructor
public class PlanningConfig implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planningConfigId;
    private int checkInDelay;
    private int checkOutDelay;
    private int endCheckIn;

    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "planning_id")
    @JsonIgnoreProperties(value ={"planningConfigs"} , allowSetters = true)
    private Planning planning;

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
