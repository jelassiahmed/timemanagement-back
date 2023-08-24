package com.timemanagemenet.timemanagementapp.Entity;


import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE  EMPLOYEE SET is_deleted = 1 where id_employee=?")
@Table(name="EMPLOYEE")
@Where(clause = "is_deleted=0")
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_employee", nullable = false)
    private Long idEmployee;

    @Column(name = "keycloak_user_id", nullable = false)
    private String keycloakUserId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Employee employee = (Employee) o;
        return getIdEmployee() != null && Objects.equals(getIdEmployee(), employee.getIdEmployee());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
