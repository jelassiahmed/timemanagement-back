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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "planning")
@SQLDelete(sql = "UPDATE  planning SET is_deleted = 1 where=?")
@Where(clause = "is_deleted=0")
@AllArgsConstructor
@NoArgsConstructor
public class Planning implements Serializable {
    @Id
    @Column(name= "planning_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planningId;
    private String planningName;
    private String planningDesc;
    private Boolean showPlanning;
    private Date startDate;
    private Date endDate;
    private String color;
    private String colorIcon;
    private int repeatCycle;
    @ElementCollection
    @CollectionTable(name = "planning_schedule_days", joinColumns = @JoinColumn(name = "planning_id"))
    @Column(name = "schedule_day")
    private Set<String> scheduleDays;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "planning_id")
    @JsonIgnoreProperties(value ="planning" , allowSetters = true)
    private List<PlanningConfig> planningConfigs = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "schedule_id")
    @JsonIgnoreProperties(value ={"plannings"} , allowSetters = true)
    private Schedule schedule;

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
