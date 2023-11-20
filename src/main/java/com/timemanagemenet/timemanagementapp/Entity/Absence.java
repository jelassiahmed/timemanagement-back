package com.timemanagemenet.timemanagementapp.Entity;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@AllArgsConstructor
@Table(name="ABSENCE")
@NoArgsConstructor
@SQLDelete(sql = "UPDATE  ABSENCE SET is_deleted = 1 where id_absence=?")
@Where(clause = "is_deleted=0")
@Entity
@Getter
@Setter
@ToString
public class Absence implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_absence")
    private Long idAbsence;

    @Column(name = "absence_date")
    private LocalDate absenceDate;

    @Column(name = "absence_type")
    private String absenceType;

    @Column(name = "keycloak_user_id")
    private String keycloakUserId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "is_deleted")
    private int isDeleted;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "updated_by")
    private String updatedBy;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Absence absence = (Absence) o;
        return getIdAbsence() != null && Objects.equals(getIdAbsence(), absence.getIdAbsence());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
