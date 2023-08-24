package com.timemanagemenet.timemanagementapp.Entity;

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
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "planning_config")
@SQLDelete(sql = "UPDATE  planning_config SET is_deleted planning_config_id= 1 where=?")
@Where(clause = "is_deleted=0")
@AllArgsConstructor
@NoArgsConstructor
public class PlanningConfig implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "planning_config_id")
    private Long planningConfigId;
    @Column(name = "check_in_delay")
    private int checkInDelay;
    @Column(name = "check_out_delay")
    private int checkOutDelay;
    @Column(name = "end_check_in")
    private int endCheckIn;

    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "planning_id")
    @JsonIgnoreProperties(value ={"planningConfigs"} , allowSetters = true)
    private Planning planning;

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
        PlanningConfig that = (PlanningConfig) o;
        return getPlanningConfigId() != null && Objects.equals(getPlanningConfigId(), that.getPlanningConfigId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
