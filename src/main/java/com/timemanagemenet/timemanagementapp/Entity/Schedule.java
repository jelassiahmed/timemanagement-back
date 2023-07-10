package com.timemanagemenet.timemanagementapp.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "scheduleId")
    private Long scheduleId;
    private int startHour;
    private int endHour;
    private boolean pauseTime;
    private int pauseStart;
    private int pauseEnd;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    @JsonIgnoreProperties(value ="schedule" , allowSetters = true)
    private List<Planning> plannings = new ArrayList<>();

}
