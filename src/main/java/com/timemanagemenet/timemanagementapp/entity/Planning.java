package com.timemanagemenet.timemanagementapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "planning")
@SQLDelete(sql = "UPDATE  planning SET is_deleted = 1 where planning_Id=?")
@Where(clause = "is_deleted=0")
@AllArgsConstructor
@NoArgsConstructor
public class Planning implements Serializable {
    @Id
    @Column(name= "planning_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planningId;

    @Column(name = "planning_name")
    private String planningName;

    @Column(name = "planning_desc")
    private String planningDesc;

    @Column(name ="show_planning")
    private Boolean showPlanning;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "color")
    private String color;

    @Column(name = "color_icon")
    private String colorIcon;

    @Column(name = "repeat_cycle")
    private int repeatCycle;
    @ElementCollection
    @CollectionTable(name = "planning_schedule_days", joinColumns = @JoinColumn(name = "planning_id"))
    @Column(name = "schedule_day")
    private Set<String> scheduleDays;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "planning_id")
    @JsonIgnoreProperties(value ="planning" , allowSetters = true)
    @ToString.Exclude
    private List<PlanningConfig> planningConfigs = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "schedule_id")
    @JsonIgnoreProperties(value ={"plannings"} , allowSetters = true)
    private Schedule schedule;

    @Column(name = "is_deleted")
    private Integer isDeleted;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "updated_by")
    private String updatedBy;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Planning planning = (Planning) o;
        return getPlanningId() != null && Objects.equals(getPlanningId(), planning.getPlanningId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
