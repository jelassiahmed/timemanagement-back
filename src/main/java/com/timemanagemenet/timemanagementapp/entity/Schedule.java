package com.timemanagemenet.timemanagementapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@Table(name = "schedule")
@SQLDelete(sql = "UPDATE  schedule SET is_deleted = 1 where scheduleId =?")
@Where(clause = "is_deleted=0")
@NoArgsConstructor
@AllArgsConstructor
public class Schedule implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "scheduleId")
    private Long scheduleId;
    @Column(name = "start_hour")
    private int startHour;
    @Column(name = "end_hour")
    private int endHour;
    @Column(name = "pause_time")
    private boolean pauseTime;
    @Column(name = "pause_start")
    private int pauseStart;
    @Column(name = "pause_end")
    private int pauseEnd;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    @JsonIgnoreProperties(value ="schedule" , allowSetters = true)
    @ToString.Exclude
    private List<Planning> plannings = new ArrayList<>();

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
        Schedule schedule = (Schedule) o;
        return getScheduleId() != null && Objects.equals(getScheduleId(), schedule.getScheduleId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
