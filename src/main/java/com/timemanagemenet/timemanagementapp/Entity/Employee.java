package com.timemanagemenet.timemanagementapp.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE  EMPLOYEE SET is_deleted = 1 where id_employee=?")
@Table(name="EMPLOYEE")
@Where(clause = "is_deleted=0")
@Entity
@Data
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_employee", nullable = false)
    private Long idEmployee;

    @Column(name = "keycloak_user_id", nullable = false)
    private String keycloakUserId;


}
