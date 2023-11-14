package com.timemanagemenet.timemanagementapp.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "departement")
@SQLDelete(sql = "UPDATE  departement SET is_deleted = 1 where departement_id=?")
@Where(clause = "is_deleted=0")
@AllArgsConstructor
@NoArgsConstructor
public class Departement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long departementId;

    @Column(name = "departement_name")
    private String departementName;

    @Column(name = "departement_manager_id")
    private String departementManagerId;

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

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "planning_planning_id")
    private Planning planning;

    @ManyToOne
    @JoinColumn(name = "parent_departement_id")
    @JsonBackReference
    private Departement parentDepartement;

    @OneToMany(mappedBy = "parentDepartement")
    @JsonManagedReference
    private List<Departement> childDepartements;




}
