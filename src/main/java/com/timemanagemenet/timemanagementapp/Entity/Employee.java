package com.timemanagemenet.timemanagementapp.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "USER_ENTITY")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    private String firstName;
    private String lastName;
    private String departementName;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    @JsonIgnoreProperties(value ="user" , allowSetters = true)
    private List<Absence> absences = new ArrayList<>() ;
}
