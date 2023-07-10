package com.timemanagemenet.timemanagementapp.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanningConfig {
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

}
